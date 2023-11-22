package com.example.authorizationserverac.dto;

import com.example.authorizationserverac.entity.ResourceOwnerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResourceOwnerDTO(@NotBlank String username,
                               @NotBlank String password,
                               @NotBlank String email,
                               @NotNull ResourceOwnerType resourceOwnerType) {
}
