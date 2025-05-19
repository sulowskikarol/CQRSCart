package com.sulowskikarol.cartservice.domain.model;

import com.sulowskikarol.cartservice.domain.model.enums.CartStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
public class Cart {

    private final UUID id;
    private final UUID userId;
    private final List<CartItem> items = new ArrayList<>();
    private CartStatus status = CartStatus.ACTIVE;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Cart(UUID id, UUID userId) {
        this.id = id;
        this.userId = userId;
    }

    private void addProduct(CartItem item) {
        assertActive();

        CartItem existing = items.stream()
                .filter(i -> i.getProductId().equals(item.getProductId()))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(item.getQuantity());
        } else {
            items.add(item);
        }

        touch();
    }

    public void removeProduct(UUID productId) {
        assertActive();

        boolean removed = items.removeIf(item -> item.getProductId().equals(productId));
        if (!removed) {
            throw new IllegalStateException("Product not found in cart");
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
