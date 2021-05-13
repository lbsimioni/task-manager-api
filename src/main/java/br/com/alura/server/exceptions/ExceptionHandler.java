package br.com.alura.server.exceptions;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.err.println("[SERVER] Server Application Failed in " + thread.getName() + " ---");
        System.err.println("[SERVER] Exception: " + throwable.getMessage());
    }
}
