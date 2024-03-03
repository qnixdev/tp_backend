package com.trade_platform.Request.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import java.util.UUID;

@Getter
public class UserDeleteRequest {
    @NotBlank
    private UUID id;

    private boolean isSoftDelete;
}