package com.sulowskikarol.cartservice.application.command.common;

public interface CommandHandler<C extends Command<R>, R> {

    R handle(C command);
}
