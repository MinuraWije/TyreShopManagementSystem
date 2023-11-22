package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.EmployeeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerModel;
import model.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton homeBtn;

    @FXML
    private JFXButton customerBtn;

    @FXML
    private JFXButton orderBtn;

    @FXML
    private JFXButton itemBtn;

    @FXML
    private JFXButton supplierBtn;

    @FXML
    private JFXButton employeeBtn;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private AnchorPane employeePane;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtRole;

    ObservableList<EmployeeDTO> observableList = FXCollections.observableArrayList();


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();

        try {
            boolean isRemoved = EmployeeModel.delete(employeeId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtEmployeeId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtNumber.setText("");
                txtEmail.setText("");
                txtRole.setText("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Delete failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        Integer telNum = Integer.valueOf(txtNumber.getText());
        String email = txtEmail.getText();
        String role = txtRole.getText();

        try {
            boolean isSaved = EmployeeModel.save(new EmployeeDTO(employeeId, name, address, telNum, email, role));


            if (isSaved) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                txtEmployeeId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtNumber.setText("");
                txtEmail.setText("");
                txtRole.setText("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        Integer telNum = Integer.valueOf(txtNumber.getText());
        String email = txtEmail.getText();
        String role = txtRole.getText();

        boolean isUpdated = false;
        try {
            isUpdated = EmployeeModel.update(new EmployeeDTO(employeeId, name, address,telNum,email, role));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtEmployeeId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtNumber.setText("");
                txtEmail.setText("");
                txtRole.setText("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/viewEmployeeForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void customerBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void homeBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void itemBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void orderBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeOrderForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void paymentBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/paymentForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void txtEmployeeIdSearchOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();

        try {
            EmployeeDTO employeeDTO= EmployeeModel.search(employeeId);

            if (employeeDTO != null) {
                txtEmployeeId.setText(employeeDTO.getEmployeeId());
                txtName.setText(employeeDTO.getName());
                txtAddress.setText(employeeDTO.getAddress());
                txtNumber.setText(String.valueOf(employeeDTO.getTelNum()));
                txtEmail.setText(employeeDTO.getEmail());
                txtRole.setText(employeeDTO.getRole());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
