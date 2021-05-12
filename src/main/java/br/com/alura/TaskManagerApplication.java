package br.com.alura;

import br.com.alura.client.TaskClient;
import br.com.alura.server.TaskServer;

public class TaskManagerApplication {
    public static void main(String[] args) {
        new Thread(new TaskServer()).start();
        new Thread(new TaskClient()).start();
    }
}
