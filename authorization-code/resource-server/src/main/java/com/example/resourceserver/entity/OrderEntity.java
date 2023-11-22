package com.example.resourceserver.entity;


import com.example.resourceserver.dto.OrderDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`ORDER`")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String ownerId;

    private String description;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total;

    public static OrderEntity valueOf(OrderDTO orderDTO) {
        return OrderEntity.builder()
                .description(orderDTO.description())
                .status(OrderStatus.CREATED)
                .total(orderDTO.total())
                .build();
    }
}
