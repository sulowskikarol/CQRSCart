package com.sulowskikarol.cartservice.application.command.removeProduct;

import com.sulowskikarol.cartservice.application.command.common.Command;

import java.util.UUID;

public record RemoveProductCommand(UUID userId, UUID productId, int quantity) implements Command<Void> {
}
