package com.sulowskikarol.cartservice.domain.model;

import com.sulowskikarol.cartservice.domain.model.enums.CartStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Cart {

    private final UUID id;
    private final UUID userId;
    private final List<CartItem> items;
    private CartStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Cart createCart(UUID userId) {
        UUID cardId = UUID.randomUUID();
        return new Cart(
                cardId,
                userId,
                new ArrayList<>(),
                CartStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public void addProduct(CartItem item) {
        assertActive();

        CartItem existing = items.stream()
                .filter(i -> i.getProductId().equals(item.getProductId()))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }

        touch();
    }

    public void removeProduct(UUID productId, int quantity) {
        assertActive();

        CartItem existing = items.stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (existing.getQuantity() - quantity < 0) {
            throw new IllegalArgumentException("Quantity less than zero after removal");
        } else if (existing.getQuantity() == quantity) {
            items.remove(existing);
        } else {
            existing.setQuantity(existing.getQuantity() - quantity);
        }

        touch();
    }

    public void finalizeCart() {
        assertActive();

        if (items.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        this.status = CartStatus.FINALIZED;
        touch();
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void assertActive() {
        if (status != CartStatus.ACTIVE)
            throw new IllegalStateException("Cart is not in active state and cannot be modified");
    }

    private void touch() {
        this.updatedAt = LocalDateTime.now();
    }
}