package com.trade_platform.Request.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;
import java.util.List;

@Getter
public class UserUpdateRequest {
    @UUID
    @NotBlank
    private java.util.UUID id;

    @Email
    @Length(max = 255)
    private String email;

    @Length(max = 255)
    private String password;

    @Length(min = 2, max = 255)
    private String fullName;

    private List<@UUID String> securityGroupIds;

    private Boolean isBlocked;
}