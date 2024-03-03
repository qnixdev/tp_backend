package com.trade_platform.Service.User;

import com.trade_platform.Entity.Enum.Status;
import com.trade_platform.Entity.SecurityGroup;
import com.trade_platform.Entity.User;
import com.trade_platform.Repository.Customer.CustomerRepository;
import com.trade_platform.Request.User.UserCreateRequest;
import com.trade_platform.Service.Customer.Exception.CustomerAlreadyExistException;
import com.trade_platform.Service.User.Exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserCreateService extends AbstractUserService {
    private final CustomerRepository customerRepository;

    @Autowired
    public UserCreateService(
        CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public User create(UserCreateRequest request, User authUser) throws Exception {
        var email = request.getEmail().toLowerCase();

        if (this.userRepository.isExistUserByEmail(email)) {
            throw new UserAlreadyExistException("email", email);
        }
        if (this.customerRepository.isExistCustomerByEmail(email)) {
            throw new CustomerAlreadyExistException("email", email);
        }

        var user = new User();
        user.setEmail(email);
        user.setFullName(request.getFullName());
        user.setStatus(Status.ACTIVE);

        var securityGroupList = request.getSecurityGroupIds()
            .stream()
            .map(uuid -> this.securityGroupRepository.findById(uuid).orElseThrow(() -> this.getSecurityGroupError(uuid)))
            .toList()
        ;

        for (SecurityGroup sg : securityGroupList) {
            user.addSecurityGroup(sg);
        }

        return user;
    }

    private NoSuchElementException getSecurityGroupError(UUID id) {
        return new NoSuchElementException(
            String.format("Security group with id: %s not found!", id)
        );
    }
}