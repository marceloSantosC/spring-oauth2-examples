package com.example.authorizationserverac.dto;

import com.example.authorizationserverac.entity.ResourceOwnerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResourceOwnerTypeDTO(@NotNull ResourceOwnerType resourceOwnerType,
                                   @NotBlank String username) {
}
