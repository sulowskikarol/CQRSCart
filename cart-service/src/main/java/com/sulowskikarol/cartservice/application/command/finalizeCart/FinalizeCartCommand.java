package com.sulowskikarol.cartservice.application.command.finalizeCart;

import com.sulowskikarol.cartservice.application.command.common.Command;

import java.util.UUID;

public record FinalizeCartCommand(UUID userId) implements Command<Void> {
}
