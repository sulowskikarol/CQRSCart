package com.sulowskikarol.cartservice.application.command.removeProduct;

import com.sulowskikarol.cartservice.application.command.common.CommandHandler;
import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveProductCommandHandler implements CommandHandler<RemoveProductCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    public Void handle(RemoveProductCommand command) {
        Cart cart = cartRepository.findActiveByUserId(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("Active cart for this user not found"));

        cart.removeProduct(command.productId(), command.quantity());
        cartRepository.save(cart);

        return null;
    }
}
