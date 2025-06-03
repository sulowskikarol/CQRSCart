package com.sulowskikarol.cartservice.application.command.common;

import jakarta.persistence.OptimisticLockException;
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
        int maxRetry = 3;
        CommandHandler<C, R> handler = (CommandHandler<C, R>) context.getBean(getHandlerClass(command));

        for (int attempt = 0; attempt < maxRetry; attempt++) {
            try {
                return handler.handle(command);
            } catch (OptimisticLockException ex) {
                if (attempt == maxRetry - 1) {
                    throw new RuntimeException("Cannot execute command:" + ex);
                }
            }
        }
        return null;
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
