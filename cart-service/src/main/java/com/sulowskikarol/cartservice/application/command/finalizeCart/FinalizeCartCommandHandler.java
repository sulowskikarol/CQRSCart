package com.sulowskikarol.cartservice.application.command.finalizeCart;

import com.sulowskikarol.cartservice.application.command.common.CommandHandler;
import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinalizeCartCommandHandler implements CommandHandler<FinalizeCartCommand, Void> {

    private final CartRepository cartRepository;

    @Override
    public Void handle(FinalizeCartCommand command) {
        Cart cart = cartRepository.findActiveByUserId(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("Active cart for this user not found"));

        cart.finalizeCart();
        cartRepository.save(cart);

        return null;
    }
}
