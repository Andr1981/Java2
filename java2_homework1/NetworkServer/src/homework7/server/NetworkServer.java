package homework7.server;

import homework7.server.auth.AuthService;
import homework7.server.auth.BaseAuthService;
import homework7.server.client.ClientHandler;
import homework8.client.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkServer {
    private static final Logger LOGGER = Logger.getLogger(NetworkServer.class.getName());
    private int port;
    private final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private final AuthService authService;

    public NetworkServer(int port) {
        this.port = port;
        this.authService = new BaseAuthService();
    }

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.log(Level.WARNING, "Сервер был успешно запущен на порту " + port);
            authService.start();
            while (true) {
                LOGGER.log(Level.INFO, "Ожидание клиентского подключения...");
                Socket clientSocket = serverSocket.accept();
                LOGGER.log(Level.WARNING, "Клиент подключился...");

                createClientHandler(clientSocket);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при работе с сервером", e);
        } finally {
            authService.stop();
        }

    }

    private void createClientHandler(Socket clientSocket) {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.run();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void broadcastMessage(Command message, ClientHandler owner) throws IOException {
        for (ClientHandler client : clients) {
            if (client != owner)
                client.sendMessage(message);
        }

    }

    public synchronized void subscribe(ClientHandler clientHandler) throws IOException {
        clients.add(clientHandler);
        List<String> users = getAllUsernames();
        broadcastMessage(Command.updateUsersListCommand(users), null);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) throws IOException {
        clients.remove(clientHandler);
        List<String> users = getAllUsernames();
        broadcastMessage(Command.updateUsersListCommand(users), null);
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new LinkedList<>();
        for (ClientHandler clientHandler : clients) {
            usernames.add(clientHandler.getNickname());
        }
        return usernames;
    }

    public synchronized void sendMessage(String receiver, Command commandMessage) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(receiver)) {
                client.sendMessage(commandMessage);
            }
        }
    }

    public boolean isNicknameBusy(String username) {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
