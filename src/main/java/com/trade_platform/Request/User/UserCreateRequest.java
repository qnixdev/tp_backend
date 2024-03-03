package com.trade_platform.Request.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import java.util.UUID;

@Getter
public class UserCreateRequest {
    @Email
    @NotBlank
    @Length(min = 5, max = 255)
    private String email;

    @NotBlank
    @Length(max = 255)
    private String password;

    @NotBlank
    @Length(min = 2, max = 255)
    private String fullName;

    @NotEmpty
    private List<UUID> securityGroupIds;
}