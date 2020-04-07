package homework7.client.view.authform;

import homework7.client.controller.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientAuthController {
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;

    private ClientController controller;

    public void initialize(){

    }

    public void authButtonOnAction(ActionEvent actionEvent) {
        String loginStr = login.getText().trim();
        String passStr = password.getText().trim();

        try {
            controller.sendAuthMessage(loginStr, passStr);
        } catch (IOException e) {
            showInfo("Ошибка!!!", "Ошибка при попытки аутентификации");
        }

    }

    public void closeBtnOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void showInfo(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

}
