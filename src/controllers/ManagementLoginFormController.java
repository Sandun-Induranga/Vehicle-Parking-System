package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;

public class ManagementLoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane loginContext;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("admin") && txtPassword.getText().equals("admin")) {
            loginContext.getChildren().clear();
            Stage stage = (Stage) loginContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ManagerMainForm.fxml"))));
            Notifications.create().title("Successful").text("Logged in").darkStyle().position(Pos.TOP_CENTER).showInformation();
        }else {
            new Alert(Alert.AlertType.WARNING,"Invalid User Name or Password", ButtonType.OK).show();
            txtUserName.setText(null);
            txtPassword.setText(null);
            Notifications.create().title("Unsuccessful").text("Wrong Details").darkStyle().position(Pos.TOP_CENTER).showWarning();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
