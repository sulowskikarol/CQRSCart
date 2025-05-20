package com.sulowskikarol.cartservice.domain.repository;

import com.sulowskikarol.cartservice.domain.model.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {

    Optional<Cart> findById(UUID cartId);
    Optional<Cart> findActiveByUserId(UUID userId);
    Cart save(Cart cart);
}
