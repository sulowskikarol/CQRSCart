package com.sulowskikarol.cartservice.application.query.common;

public interface QueryHandler<Q extends Query<R>, R> {

    R handle(Q query);
}
