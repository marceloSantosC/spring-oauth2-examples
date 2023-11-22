package com.example.resourceserver.dto;

import com.example.resourceserver.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusDTO(@NotNull OrderStatus orderStatus) {

}
