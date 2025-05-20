package com.sulowskikarol.cartservice.application.command.createCart;

import com.sulowskikarol.cartservice.application.command.common.CommandHandler;
import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateCartCommandHandler implements CommandHandler<CreateCartCommand, UUID> {

    private final CartRepository cartRepository;

    @Override
    public UUID handle(CreateCartCommand command) {
        Optional<Cart> existingCart = cartRepository.findActiveByUserId(command.userId());
        if (existingCart.isPresent()) throw new IllegalStateException("User already has active cart");

        Cart cart = Cart.createCart(command.userId());
        return cartRepository.save(cart);
    }
}
