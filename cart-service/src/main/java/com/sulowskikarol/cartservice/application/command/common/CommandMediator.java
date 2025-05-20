package com.sulowskikarol.cartservice.application.command.common;

public interface CommandMediator {

    <R, C extends Command<R>> R send(C command);
}
