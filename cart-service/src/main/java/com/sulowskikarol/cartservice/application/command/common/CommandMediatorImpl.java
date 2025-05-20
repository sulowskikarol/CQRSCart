package com.sulowskikarol.cartservice.application.command.common;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandMediatorImpl implements CommandMediator {

    private final ApplicationContext context;

    @Override
    @SuppressWarnings("unchecked")
    public <R, C extends Command<R>> R send(C command) {
        CommandHandler<C, R> handler = (CommandHandler<C, R>) context.getBean(getHandlerClass(command));
        return handler.handle(command);
    }

    private <C extends Command<?>> Class<?> getHandlerClass(C command) {
        String className = command.getClass().getSimpleName() + "Handler";
        String packageName = command.getClass().getPackageName() + ".";
        try {
            return Class.forName(packageName + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Handler class not found: " + className, e);
        }
    }
}
