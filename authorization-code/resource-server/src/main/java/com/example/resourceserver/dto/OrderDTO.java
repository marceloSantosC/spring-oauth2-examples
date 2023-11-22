package com.example.resourceserver.dto;

import java.math.BigDecimal;

public record OrderDTO(String description,
                       BigDecimal total) {
}
