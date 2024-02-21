package com.trade_platform.Request.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.UUID;

@Getter
public class UserDeleteRequest {
    @UUID
    @NotBlank
    private java.util.UUID id;

    private boolean isSoftDelete;
}