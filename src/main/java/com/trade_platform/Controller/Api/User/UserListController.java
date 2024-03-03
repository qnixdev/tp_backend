package com.trade_platform.Controller.Api.User;

import com.trade_platform.Controller.Api.ApiController;
import com.trade_platform.Entity.User;
import com.trade_platform.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserListController extends ApiController {
    private final UserRepository userRepository;

    @Autowired
    public UserListController(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> list() {
        return this.userRepository.findAll();
    }
}