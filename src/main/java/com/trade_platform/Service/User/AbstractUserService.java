package com.trade_platform.Service.User;

import com.trade_platform.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractUserService {
    @Autowired
    protected UserRepository userRepository;
}