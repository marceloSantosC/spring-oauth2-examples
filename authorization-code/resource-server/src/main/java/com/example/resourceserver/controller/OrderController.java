package com.example.resourceserver.controller;

import com.example.resourceserver.config.CanModifyOrCreateOrders;
import com.example.resourceserver.config.CanReadOwnOrders;
import com.example.resourceserver.dto.OrderDTO;
import com.example.resourceserver.dto.OrderInfoDTO;
import com.example.resourceserver.dto.UpdateOrderStatusDTO;
import com.example.resourceserver.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/me/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CanModifyOrCreateOrders
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody @Valid OrderDTO orderDTO, @AuthenticationPrincipal Jwt jwt) {

        var userId = Optional.ofNullable(jwt)
                .map(token -> token.getClaimAsString("resource_owner_id"))
                .filter(StringUtils::hasText)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));

        orderService.createOrder(orderDTO, userId);
    }


    @GetMapping
    @CanReadOwnOrders
    @ResponseStatus(HttpStatus.OK)
    public List<OrderInfoDTO> getAllOrders(@AuthenticationPrincipal Jwt jwt) {

        // O nome da claim poderia ser uma property e a lógica poderia ser extraida para uma service usando SecurityContextHolder
        var userId = Optional.ofNullable(jwt)
                .map(token -> token.getClaimAsString("resource_owner_id"))
                .filter(StringUtils::hasText)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));

       return orderService.getOrdersByOwnerId(userId);
    }

    @PutMapping("/{orderId}")
    @CanModifyOrCreateOrders
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable String orderId, UpdateOrderStatusDTO updateStatusDTO) {
        orderService.updateStatus(orderId, updateStatusDTO);
    }



}
