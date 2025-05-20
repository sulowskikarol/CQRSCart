package com.sulowskikarol.cartservice.application.query.common;


import com.sulowskikarol.cartservice.application.command.common.Command;

public interface QueryMediator {

    <R, Q extends Query<R>> R send(Q query);

}
