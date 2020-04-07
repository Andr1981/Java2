package homework7.client.view.chatform;

import homework7.client.controller.ClientController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class ClientChatController {
    public TextField newNickname;
    @FXML
    private TextField enteredText;

    @FXML
    private TextArea chatArea;

    @FXML
    private ListView<String> usersList;

    private ClientController controller;

    public void initialize(){

    }

    public void sendBtnAction(ActionEvent actionEvent) {
        String message  = enteredText.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        //пишем в поле введенный нами текст и контакт сразу пишет ответ
        appendOwnMessage(message);

        if(usersList.getSelectionModel().getSelectedIndex() <1){
            controller.sendMessageToAllUsers(message);
        }else{
            String username = usersList.getSelectionModel().getSelectedItem();
            controller.sendPrivateMessage(username, message);
        }
        enteredText.clear();
    }

    private void appendOwnMessage(String message) {
        appendMessage("Я: " + message);
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public void appendMessage(String message) {
        Platform.runLater(()-> {
            chatArea.appendText(message);
            chatArea.appendText(System.lineSeparator());
        });
    }

    public void updateUsers(List<String> users) {
        Platform.runLater(()-> {
            ObservableList<String> contacts = FXCollections.observableArrayList(users);
            usersList.setItems(contacts);
        });
    }

    public void showInfo(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void changeNickName(ActionEvent actionEvent) {
        String newNick = newNickname.getText().trim();
        if (newNick.isEmpty()){
            showInfo("Ошибка!!", "Новый ник пустой");
            return;
        }

        controller.sendChangeNicknameCommand(newNick);
        newNickname.clear();
    }
}
