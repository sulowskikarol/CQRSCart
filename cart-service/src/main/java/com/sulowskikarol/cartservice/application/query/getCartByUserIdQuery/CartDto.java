package com.sulowskikarol.cartservice.application.query.getCartByUserIdQuery;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartDto(
        UUID id,
        List<CartItemDto> items,
        BigDecimal total
) {
    public record CartItemDto(
            UUID productId,
            int quantity,
            BigDecimal unitPrice
    ) {}
}
