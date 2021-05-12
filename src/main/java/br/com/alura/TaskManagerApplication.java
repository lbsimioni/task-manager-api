package br.com.alura;

import br.com.alura.client.TaskClient;
import br.com.alura.server.TaskServer;

import java.io.IOException;

public class TaskManagerApplication {
    public static void main(String[] args) throws IOException {
        new Thread(new TaskServer()).start();
        new Thread(new TaskClient()).start();
    }
}
