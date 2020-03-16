package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGTH = 400;
    private JPanel contacts = new JPanel();
    private JPanel panel = new JPanel();
    private JTextField fieldInput = new JTextField();
    private JTextArea log = new JTextArea();
    private JButton button = new JButton("Send");
    private JList<String> usersList;


    public static void main(String[] args) {
        ChatWindow chatWindow = new ChatWindow();
        chatWindow.setVisible(true);
    }

    public ChatWindow() {
        super("ChatRoom");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        contacts.setPreferredSize(new Dimension(100, 400));
        fieldInput.setPreferredSize(new Dimension(500,30));
        log.setEditable(false);
        log.setLineWrap(true);


        panel.setLayout(new FlowLayout());
        panel.add(fieldInput);
        panel.add(button);
        add(contacts, BorderLayout.WEST);

        fieldInput.addActionListener(this);
        button.addActionListener(this);
        add(panel,BorderLayout.SOUTH);
        add(log, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if (msg.equals("")) {
            return;
        }
        fieldInput.setText(null);
        appendOwnMsg(msg);

    }



    public void appendOwnMsg(String msg) {
        appendMsg("Я ", msg);
    }

    public void appendMsg(String sender, String message) {
        String formattedMessage = String.format("%s: %s%n", sender, message);
        log.append(formattedMessage);
    }


}
