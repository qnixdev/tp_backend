package com.trade_platform.Request.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;
import java.util.List;

@Getter
public class UserCreateRequest {
    @Email
    @NotBlank
    @Length(max = 255)
    private String email;

    @NotBlank
    @Length(max = 255)
    private String password;

    @NotBlank
    @Length(min = 2, max = 255)
    private String fullName;

    @NotEmpty
    private List<@UUID String> securityGroupIds;
}