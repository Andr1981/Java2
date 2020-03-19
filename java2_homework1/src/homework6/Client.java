package homework6;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGTH = 400;
    private JPanel contacts = new JPanel();
    private JPanel panel = new JPanel();
    private JTextField fieldInput = new JTextField();
    private JTextArea log = new JTextArea();
    private JButton button = new JButton("Send");


    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8188;
    private Socket clientSocket = null;
    private DataInputStream in;
    private DataOutputStream out;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client();
            }
        });

    }

    public Client() {
        super("ChatRoom");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        contacts.setPreferredSize(new Dimension(100, 400));
        fieldInput.setPreferredSize(new Dimension(500, 30));
        log.setEditable(false);
        log.setLineWrap(true);


        panel.setLayout(new FlowLayout());
        panel.add(fieldInput);
        panel.add(button);
        add(contacts, BorderLayout.WEST);


        fieldInput.addActionListener(e -> sendMessage());
        button.addActionListener(e -> sendMessage());
        add(panel, BorderLayout.SOUTH);
        add(log, BorderLayout.CENTER);
        setVisible(true);

        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private void sendMessage() {
        if (!fieldInput.getText().trim().isEmpty()) {
            try {
                out.writeUTF(fieldInput.getText());
                fieldInput.setText("");
                fieldInput.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

    private void openConnection() throws IOException {
        clientSocket = new Socket(SERVER_ADDR, SERVER_PORT);
        System.out.println("Клиент запущен...");
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.equalsIgnoreCase("/end")) {
                        break;
                    }
                    log.append(strFromServer);
                    log.append("\n");
                }
            } catch (Exception e) {
                System.out.println("Connection has been closed!");
            }
        }).start();

    }


}
