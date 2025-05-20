package com.sulowskikarol.cartservice.application.query.common;

import com.sulowskikarol.cartservice.application.command.common.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueryMediatorImpl implements QueryMediator {

    private final ApplicationContext context;

    @Override
    @SuppressWarnings("unchecked")
    public <R, Q extends Query<R>> R send(Q query) {
        QueryHandler<Q, R> handler = (QueryHandler<Q, R>) context.getBean(getHandlerClass(query));
        return handler.handle(query);
    }

    private <Q extends Query<?>> Class<?> getHandlerClass(Q query) {
        String className = query.getClass().getSimpleName() + "Handler";
        String packageName = query.getClass().getPackageName() + ".";
        try {
            return Class.forName(packageName + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Handler class not found: " + className, e);
        }
    }
}
