package com.trade_platform.Controller.User;

import com.trade_platform.Entity.User;
import com.trade_platform.Request.User.*;
import com.trade_platform.Service.User.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserCrudController {
    private final UserCreateService userCreateService;

    @Autowired
    public UserCrudController(
        UserCreateService userCreateService
    ) {
        this.userCreateService = userCreateService;
    }

    @PutMapping
    public void create(@Valid @RequestBody UserCreateRequest request) {
        try {
            this.userCreateService.create(request, null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public User read(@PathVariable UUID id) {
        return new User();
    }

    @PostMapping
    public void update(@Valid @RequestBody UserUpdateRequest request) {}

    @DeleteMapping
    public void delete(@Valid @RequestBody UserDeleteRequest request) {}
}