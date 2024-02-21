package com.trade_platform.Controller.User;

import com.trade_platform.Entity.User;
import com.trade_platform.Service.User.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserListController {
    private final UserCrudService userCrudService;

    @Autowired
    public UserListController(
        UserCrudService userCrudService
    ) {
        this.userCrudService = userCrudService;
    }

    @GetMapping
    public Iterable<User> list() {
        return this.userCrudService.list();
    }
}