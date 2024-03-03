package com.trade_platform.Request.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import java.util.UUID;

@Getter
public class UserUpdateRequest {
    @NotBlank
    private UUID id;

    @Email
    @Length(min = 5, max = 255)
    private String email;

    @Length(max = 255)
    private String password;

    @Length(min = 2, max = 255)
    private String fullName;

    private List<UUID> securityGroupIds;

    private Boolean isBlocked;
}