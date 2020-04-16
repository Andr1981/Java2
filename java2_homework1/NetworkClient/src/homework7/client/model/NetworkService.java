package homework7.client.model;

import homework7.client.controller.AuthEvent;
import homework7.client.controller.ClientController;
import homework7.client.controller.MessageHandler;
import homework8.client.Command;
import homework8.client.command.*;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class NetworkService {
    private final String host;
    private final int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ClientController controller;

    private MessageHandler messageHandler;
    private AuthEvent successfulAuthEvent;
    private String nickname;

    public NetworkService(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect(ClientController controller) throws IOException {
        this.controller = controller;
        this.socket = new Socket(host, port);
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        runReadThread();
    }

    public void runReadThread() {

        new Thread(() -> {
            while (true) {
                try {
                    Command command = (Command) in.readObject();
                    switch (command.getType()) {
                        case AUTH: {
                            AuthCommand commandData = (AuthCommand) command.getData();
                            nickname = commandData.getUsername();
                            String login = commandData.getLogin();
                            successfulAuthEvent.authIsSuccessful(login, nickname);
                            break;
                        }
                        case CHANGE_NICKNAME: {
                            ChangeNicknameCommand commandData = (ChangeNicknameCommand) command.getData();
                            nickname = commandData.getUsername();
                            controller.updateNickname(nickname);
                            break;
                        }
                        case MESSAGE: {
                            MessageCommand commandData = (MessageCommand) command.getData();
                            if (messageHandler != null) {
                                String message = commandData.getMessage();
                                String username = commandData.getUsername();
                                if (username != null) {
                                    message = username + ":" + message;
                                }
                                messageHandler.handle(message);
                            }
                            break;
                        }
                        case AUTH_ERROR:
                        case ERROR: {
                            ErrorCommand commandData = (ErrorCommand) command.getData();
                            controller.showErrorMessage(commandData.getErrorMessage());
                            break;
                        }
                        case UPDATE_USERS_LIST: {
                            UpdateUsersListCommand commandData = (UpdateUsersListCommand) command.getData();
                            List<String> users = commandData.getUsers();
                            controller.updateUsersList(users);
                            break;
                        }
                        default:
                            System.err.println("Unknown type of command: " + command.getType());
                    }


                } catch (IOException e) {
                    System.out.println("Поток чтения был прерван!");
                    e.printStackTrace();
                    return;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public void sendCommand(Command command) throws IOException {
        out.writeObject(command);
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void setSuccessfulAuthEvent(AuthEvent successfulAuthEvent) {
        this.successfulAuthEvent = successfulAuthEvent;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
