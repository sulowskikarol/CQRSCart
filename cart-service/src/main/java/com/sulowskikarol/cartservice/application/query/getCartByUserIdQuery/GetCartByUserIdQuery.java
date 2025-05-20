package com.sulowskikarol.cartservice.application.query.getCartByUserIdQuery;

import com.sulowskikarol.cartservice.application.query.common.Query;

import java.util.UUID;

public record GetCartByUserIdQuery(UUID userId) implements Query<CartDto> {
}
