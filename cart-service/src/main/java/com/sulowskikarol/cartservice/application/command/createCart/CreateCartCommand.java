package com.sulowskikarol.cartservice.application.command.createCart;

import com.sulowskikarol.cartservice.application.command.common.Command;

import java.util.UUID;

public record CreateCartCommand(UUID userId) implements Command<UUID> {
}
