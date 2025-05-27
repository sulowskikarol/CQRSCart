package com.sulowskikarol.cartservice.application.command.addProduct;

import com.sulowskikarol.cartservice.application.command.common.CommandHandler;
import com.sulowskikarol.cartservice.domain.model.Cart;
import com.sulowskikarol.cartservice.domain.model.CartItem;
import com.sulowskikarol.cartservice.domain.repository.CartRepository;
import com.sulowskikarol.cartservice.infrastructure.product.ProductHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AddProductCommandHandler implements CommandHandler<AddProductCommand, Void> {

    private final CartRepository cartRepository;
    private final ProductHttpClient productHttpClient;

    @Override
    public Void handle(AddProductCommand command) {
        Cart cart = cartRepository.findActiveByUserId(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("Active cart for this user not found"));

        BigDecimal price = productHttpClient.getPriceByProductId(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        cart.addProduct(new CartItem(command.productId(), command.quantity(), price));
        cartRepository.save(cart);

        return null;
    }
}
