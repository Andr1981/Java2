package homework7.client.view;

import homework7.client.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientChat extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGTH = 400;
    private JPanel mainPanel = new JPanel();
    private JPanel panel = new JPanel();
    private JTextField messageTextField = new JTextField();
    private JTextArea chatText = new JTextArea();
    private JButton sendButton = new JButton("Send");
    private JList<String> usersList;

    private ClientController controller;

    public ClientChat(ClientController controller) {
        this.controller = controller;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        mainPanel.setPreferredSize(new Dimension(100, 400));
        messageTextField.setPreferredSize(new Dimension(500, 30));
        chatText.setEditable(false);



        panel.setLayout(new FlowLayout());
        panel.add(messageTextField);
        panel.add(sendButton);
        add(mainPanel, BorderLayout.WEST);
        add(panel, BorderLayout.SOUTH);
        add(chatText, BorderLayout.CENTER);

        addListeners();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controller.shutdown();
            }
        });

    }

    private void addListeners() {
        sendButton.addActionListener(e -> ClientChat.this.sendMessage());
        messageTextField.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String message = messageTextField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        appendOwnMessage(message);

        if (usersList.getSelectedIndex() < 1) {
            controller.sendMessage(message);
        } else {
            String username = usersList.getSelectedValue();
            controller.sendPrivateMessage(username, message);
        }

        messageTextField.setText(null);
    }

    public void appendMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            chatText.append(message);
            chatText.append(System.lineSeparator());
        });
    }


    private void appendOwnMessage(String message) {
        appendMessage("Я: " + message);
    }

}
