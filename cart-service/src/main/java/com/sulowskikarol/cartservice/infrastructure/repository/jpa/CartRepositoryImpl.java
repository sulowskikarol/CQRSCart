package com.sulowskikarol.cartservice.infrastructure.repository.jpa;

import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.model.enums.CartStatus;
import com.sulowskikarol.cartservice.domain.repository.CartRepository;
import com.sulowskikarol.cartservice.infrastructure.repository.entity.CartEntity;
import com.sulowskikarol.cartservice.infrastructure.repository.jpa.mapper.CartJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {

    private final CartJpaRepository cartJpaRepository;
    private final CartJpaMapper cartJpaMapper;

    @Override
    public Optional<Cart> findById(UUID cartId) {
        return cartJpaRepository.findById(cartId)
                .map(cartJpaMapper::toDomain);
    }

    @Override
    public Optional<Cart> findActiveByUserId(UUID userId) {
        return cartJpaRepository.findByUserIdAndStatus(userId, CartStatus.ACTIVE)
                .map(cartJpaMapper::toDomain);
    }

    @Override
    public UUID save(Cart cart) {
        CartEntity saved = cartJpaRepository.save(cartJpaMapper.toEntity(cart));
        return saved.getId();
    }
}
