package com.sulowskikarol.cartservice.application.command.addProduct;

import com.sulowskikarol.cartservice.application.command.common.CommandHandler;
import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.model.CartItem;
import com.sulowskikarol.cartservice.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AddProductCommandHandler implements CommandHandler<AddProductCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    public Void handle(AddProductCommand command) {
        Cart cart = cartRepository.findActiveByUserId(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("Active cart for this user not found"));

        // Placeholder: Product from other service
        BigDecimal fakePrice = new BigDecimal("99.99");

        cart.addProduct(new CartItem(command.productId(), command.quantity(), fakePrice));
        cartRepository.save(cart);

        return null;
    }
}
