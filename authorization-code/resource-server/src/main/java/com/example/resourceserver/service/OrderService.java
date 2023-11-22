package com.example.resourceserver.service;

import com.example.resourceserver.dto.OrderDTO;
import com.example.resourceserver.dto.OrderInfoDTO;
import com.example.resourceserver.dto.UpdateOrderStatusDTO;
import com.example.resourceserver.entity.OrderEntity;
import com.example.resourceserver.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderDTO orderDTO, String userId) {
        OrderEntity orderEntity = OrderEntity.valueOf(orderDTO);
        orderEntity.setOwnerId(userId);
        orderRepository.save(orderEntity);
    }

    public  List<OrderInfoDTO> getOrdersByOwnerId(String ownerId) {
        var orders = orderRepository.findAllByOwnerId(ownerId);
        return orders.stream()
                .map(OrderInfoDTO::valueOf)
                .toList();
    }

    public void updateStatus(String orderId, UpdateOrderStatusDTO updateStatusDTO) {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (order.getStatus().equals(updateStatusDTO.orderStatus())) {
            return;
        }

        order.setStatus(updateStatusDTO.orderStatus());
        orderRepository.save(order);
    }
}
