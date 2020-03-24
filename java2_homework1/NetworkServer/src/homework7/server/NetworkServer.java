package homework7.server;

import homework7.server.client.ClientHundler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetworkServer {

    private final int port;
    private final List<ClientHundler> clients = new ArrayList<>();

    public NetworkServer(int port) {
        this.port = port;
    }


    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер был успешно запущен на порту " + port);
            while (true) {
                System.out.println("Ожидание клиентского подключения.......");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился");
                clients.add(new ClientHundler(this, clientSocket));
            }

        } catch (IOException e) {
           System.out.println("Ошибка при работе сервера");
           e.printStackTrace();
        }
    }
}
