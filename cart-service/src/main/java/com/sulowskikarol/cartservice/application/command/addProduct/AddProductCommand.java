package com.sulowskikarol.cartservice.application.command.addProduct;

import com.sulowskikarol.cartservice.application.command.common.Command;

import java.util.UUID;

public record AddProductCommand(UUID userId, UUID productId, int quantity) implements Command<Void> {
}