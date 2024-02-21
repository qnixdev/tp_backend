package com.trade_platform.Service.User;

import com.trade_platform.Entity.User;
import com.trade_platform.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCrudService {
    private final UserRepository userRepository;

    @Autowired
    public UserCrudService(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public Iterable<User> list() {
        return this.userRepository.findAll();
    }
}