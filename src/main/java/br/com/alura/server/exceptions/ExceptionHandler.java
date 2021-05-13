package br.com.alura.server.exceptions;

import br.com.alura.server.TaskServer;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {
    private final TaskServer taskServer;

    public ExceptionHandler(TaskServer taskServer) {

        this.taskServer = taskServer;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.err.println("--- Server Application Failed in " + thread.getName() + " ---");
        System.err.println("Exception: " + throwable.getMessage());
    }
}
