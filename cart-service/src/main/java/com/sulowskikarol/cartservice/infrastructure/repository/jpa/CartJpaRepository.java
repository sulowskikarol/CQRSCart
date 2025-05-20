package com.sulowskikarol.cartservice.infrastructure.repository.jpa;

import com.sulowskikarol.cartservice.domain.model.enums.CartStatus;
import com.sulowskikarol.cartservice.infrastructure.repository.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {

    Optional<CartEntity> findByUserIdAndStatus(UUID userId, CartStatus status);
}
