package com.trade_platform.Service.User;

import com.trade_platform.Entity.User;
import com.trade_platform.Repository.Customer.CustomerRepository;
import com.trade_platform.Request.User.UserCreateRequest;
import com.trade_platform.Service.Customer.Exception.CustomerAlreadyExistException;
import com.trade_platform.Service.User.Exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService extends AbstractUserService {
    private final CustomerRepository customerRepository;

    @Autowired
    public UserCreateService(
        CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public void create(UserCreateRequest request, User authUser) throws Exception {
        var email = request.getEmail().toLowerCase();

        if (this.userRepository.isExistUserByEmail(email)) {
            throw new UserAlreadyExistException("email", email);
        }
        if (this.customerRepository.isExistCustomerByEmail(email)) {
            throw new CustomerAlreadyExistException("email", email);
        }
    }
}