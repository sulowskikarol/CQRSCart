package com.sulowskikarol.cartservice.infrastructure.repository.jpa;

import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.model.enums.CartStatus;
import com.sulowskikarol.cartservice.domain.repository.CartRepository;
import com.sulowskikarol.cartservice.infrastructure.repository.entity.CartEntity;
import com.sulowskikarol.cartservice.infrastructure.repository.jpa.mapper.CartJpaMapper;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public UUID save(Cart cart) {
        Optional<CartEntity> optionalCartEntity = cartJpaRepository.findById(cart.getId());

        CartEntity cartEntity = optionalCartEntity.map(entity -> {
            cartJpaMapper.updateEntity(entity, cart);
            return entity;
        }).orElseGet(() -> cartJpaMapper.toEntity(cart));

        return cartJpaRepository.save(cartEntity).getId();
    }
}
