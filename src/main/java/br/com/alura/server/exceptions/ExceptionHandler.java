package br.com.alura.server.exceptions;

import br.com.alura.server.TaskServer;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {
    private TaskServer taskServer;

    public ExceptionHandler(TaskServer taskServer) {

        this.taskServer = taskServer;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.out.println("--- Server Application Failed in " + thread.getName() + " ---");
        System.out.println("Exception: " + throwable.getMessage());
    }
}
