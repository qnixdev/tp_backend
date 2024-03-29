package com.trade_platform.Controller.Api.User;

import com.trade_platform.Controller.Api.ApiController;
import com.trade_platform.Entity.User;
import com.trade_platform.Request.User.*;
import com.trade_platform.Service.Customer.Exception.CustomerAlreadyExistException;
import com.trade_platform.Service.User.*;
import com.trade_platform.Service.User.Exception.UserAlreadyExistException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserCrudController extends ApiController {
    private final UserCreateService userCreateService;

    public UserCrudController(
        UserCreateService userCreateService
    ) {
        this.userCreateService = userCreateService;
    }

    @PutMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserCreateRequest request) {
        try {
            var user = this.userCreateService.create(request, null);

            return ResponseEntity.ok(user);
        } catch (UserAlreadyExistException|CustomerAlreadyExistException ex) {
            return this.error(ex.getMessage(), ApiController.API_ERROR_ALREADY_EXISTS, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception ex) {
            return this.error(ex.getMessage(), ApiController.API_ERROR_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable UUID id) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> update(@Valid @RequestBody UserUpdateRequest request) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@Valid @RequestBody UserDeleteRequest request) {
        return ResponseEntity.noContent().build();
    }
}