package homework7.client.controller;


import homework7.client.model.NetworkService;
import homework7.client.view.AuthDialog;
import homework7.client.view.ClientChat;
import homework8.client.Command;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ClientController {
    private final NetworkService networkService;
    private final AuthDialog authDialog;
    private final ClientChat clientChat;
    private String nickname;

    public ClientController(String serverHost, int serverPort) {
        this.networkService = new NetworkService(serverHost, serverPort,this);
        this.authDialog = new AuthDialog(this);
        this.clientChat = new ClientChat(this);
    }

    public void runApplication() throws IOException {
        connectToServer();
        runAuthProcess();
    }

    private void runAuthProcess() {
        networkService.setSuccessfulAuthEvent(nickname -> {
            setUserName(nickname);
            clientChat.setTitle(nickname);
            openChat();
        });
        authDialog.setVisible(true);

    }

    private void openChat() {
        authDialog.dispose();
        networkService.setMessageHandler(clientChat::appendMessage);
        clientChat.setVisible(true);
    }

    private void setUserName(String nickname) {
        this.nickname = nickname;
    }

    private void connectToServer() throws IOException {
        try {
            networkService.connect();
        } catch (IOException e) {
            System.err.println("Failed to establish server connection");
            throw e;
        }
    }

    public void sendAuthMessage(String login, String pass) throws IOException {
        networkService.sendCommand(Command.authCommand(login, pass));
    }

    public void sendMessageToAllUsers(String message) {
        try {
            networkService.sendCommand(Command.broadcastMessageCommand(message));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to send message!");
            e.printStackTrace();
        }
    }

    public void shutdown() {
        networkService.close();
    }

    public String getUsername() {
        return nickname;
    }

    public void sendPrivateMessage(String username, String message) {
        try {
            networkService.sendCommand(Command.privateMessageCommand(username, message));
        } catch (IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    public void showErrorMessage(String errorMessage) {
        if (clientChat.isActive()) {
            clientChat.showError(errorMessage);
        }
        else if (authDialog.isActive()) {
            authDialog.showError(errorMessage);
        }
        System.err.println(errorMessage);
    }

    public void updateUsersList(List<String> users) {
        users.remove(nickname);
        users.add(0, "All");
        clientChat.updateUsers(users);
    }
}
