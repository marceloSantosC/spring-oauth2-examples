package com.example.authorizationserverac.repository;

import com.example.authorizationserverac.entity.ResourceOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceOwnerRepository extends JpaRepository<ResourceOwnerEntity, String> {

    Optional<ResourceOwnerEntity> findByUsername(String username);

    boolean existsByUsername(String username);

}
