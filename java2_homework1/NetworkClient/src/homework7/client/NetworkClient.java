package homework7.client;

import homework7.client.controller.ClientController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class NetworkClient extends Application {

    private final String DEFAULT_ADDR = "localhost";
    private final int DEFAULT_PORT= 8189;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            ClientController clientController = new ClientController(DEFAULT_ADDR, DEFAULT_PORT, primaryStage);
            clientController.runApplication();
        } catch (IOException e) {
            System.err.println("Failed to connect to server! Please, check you network settings");
            e.printStackTrace();
        }

    }
}
