package com.example.resourceserver.repository;

import com.example.resourceserver.entity.OrderEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {


    List<OrderEntity> findAllByOwnerId(String ownerId);



}
