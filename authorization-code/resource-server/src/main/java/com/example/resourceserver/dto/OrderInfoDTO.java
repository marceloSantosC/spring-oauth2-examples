package com.example.resourceserver.dto;

import com.example.resourceserver.entity.OrderEntity;
import com.example.resourceserver.entity.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderInfoDTO(String id,
                           String description,
                           OrderStatus status,

                           BigDecimal total) {


    public static OrderInfoDTO valueOf(OrderEntity orderEntity) {
        return new OrderInfoDTO(orderEntity.getId(), orderEntity.getDescription(), orderEntity.getStatus(), orderEntity.getTotal());
    }
}
