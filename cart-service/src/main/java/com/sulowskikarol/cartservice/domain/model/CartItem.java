package com.sulowskikarol.cartservice.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "productId")
public class CartItem {

    private final UUID productId;
    private int quantity;
    private final BigDecimal unitPrice;

    public CartItem(UUID productId, int quantity, BigDecimal unitPrice) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Unit price must be greater than 0");

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void setQuantity(int newQuantity) {
        if (newQuantity <= 0) throw new IllegalArgumentException("New quantity must be greater or equal 0");
        this.quantity = newQuantity;
    }
}
