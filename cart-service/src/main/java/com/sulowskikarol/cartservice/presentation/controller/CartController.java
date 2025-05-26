package com.sulowskikarol.cartservice.presentation.controller;

import com.sulowskikarol.cartservice.application.command.addProduct.AddProductCommand;
import com.sulowskikarol.cartservice.application.command.common.CommandMediator;
import com.sulowskikarol.cartservice.application.command.createCart.CreateCartCommand;
import com.sulowskikarol.cartservice.application.command.finalizeCart.FinalizeCartCommand;
import com.sulowskikarol.cartservice.application.command.removeProduct.RemoveProductCommand;
import com.sulowskikarol.cartservice.application.query.common.QueryMediator;
import com.sulowskikarol.cartservice.application.query.getCartByUserIdQuery.CartDto;
import com.sulowskikarol.cartservice.application.query.getCartByUserIdQuery.GetCartByUserIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CommandMediator commandMediator;
    private final QueryMediator queryMediator;

    @PostMapping
    public ResponseEntity<UUID> createCart(@RequestParam UUID userId) {
        UUID cartId = commandMediator.send(new CreateCartCommand(userId));
        return ResponseEntity.status(201).body(cartId);
    }

    @PostMapping("/product")
    public ResponseEntity<Void> addProduct(
            @RequestParam UUID userId,
            @RequestParam UUID productId,
            @RequestParam int quantity) {

        commandMediator.send(new AddProductCommand(userId, productId, quantity));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product")
    public ResponseEntity<Void> removeProduct(@RequestParam UUID userId, @RequestParam UUID productId) {
        commandMediator.send(new RemoveProductCommand(userId, productId));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CartDto> getCart(@RequestParam UUID userId) {
        CartDto cart = queryMediator.send(new GetCartByUserIdQuery(userId));
        return ResponseEntity.ok(cart);
    }

    @PatchMapping
    public ResponseEntity<Void> finalizeCart(@RequestParam UUID userId) {
        commandMediator.send(new FinalizeCartCommand(userId));
        return ResponseEntity.ok().build();
    }
}
