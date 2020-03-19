package homework6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final String END_COMMAND = "/end";

    public static void main(String[] args) throws IOException {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        Thread inputThread = null;
        try {
            serverSocket = new ServerSocket(8188, 1);
            System.out.println("Сервер запущен, ожидаем подключения...");
            boolean flag = true;
            while (flag) {
                clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился");
            }
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            inputThread = runInputThread(in);
            runOtputLoop(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputThread != null) inputThread.interrupt();
            if (serverSocket != null) serverSocket.close();
            if (clientSocket != null) clientSocket.close();
        }
    }

    private static void runOtputLoop(DataOutputStream out) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String msg = scanner.next();
            out.writeUTF(msg);
            if (msg.equals("/end")) {
                break;
            }
        }

    }

    private static Thread runInputThread(DataInputStream in) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String msg = in.readUTF();
                    System.out.println("From client : " + msg);
                } catch (IOException e) {
                    System.out.println("Соединение прервано");
                    break;
                }
            }
        });
        thread.start();
        return thread;
    }
}
