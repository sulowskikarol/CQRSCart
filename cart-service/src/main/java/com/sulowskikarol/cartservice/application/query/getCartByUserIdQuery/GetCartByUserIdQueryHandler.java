package com.sulowskikarol.cartservice.application.query.getCartByUserIdQuery;

import com.sulowskikarol.cartservice.application.query.common.QueryHandler;
import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCartByUserIdQueryHandler implements QueryHandler<GetCartByUserIdQuery, CartDto> {

    private final CartRepository cartRepository;

    @Override
    public CartDto handle(GetCartByUserIdQuery query) {
        Cart cart = cartRepository.findActiveByUserId(query.userId())
                .orElseThrow(() -> new IllegalArgumentException("Active cart for this user not found"));

        BigDecimal total = cart.getTotalPrice();

        List<CartDto.CartItemDto> itemDtoList = cart.getItems().stream()
                .map(item -> new CartDto.CartItemDto(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getUnitPrice()))
                .toList();

        return new CartDto(
                cart.getId(),
                itemDtoList,
                total
        );
    }
}
