package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Check for user role based on username
        if ("user".equals(username)) {
            // User role
            String validUserPassword = "1234";

            if (password.equals(validUserPassword)) {
                // Username and password are correct for the user role, proceed to userDashboard
                loadDashboard("/view/dashboardForm.fxml");
                return; // Exit the method
            }

        }

        // Username or password is incorrect, show an error message
        showErrorDialog("Login Failed", "Invalid username or password. Please try again.");
    }
    private void loadDashboard(String dashboardFXMLPath) throws IOException {
        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource(dashboardFXMLPath));
            root.getChildren().clear();
            root.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


