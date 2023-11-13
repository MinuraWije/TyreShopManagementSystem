package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.CustomerModel;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerFormController {

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
    private AnchorPane customerPane;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTextField txtEmail;

    ObservableList<CustomerDTO> observableList = FXCollections.observableArrayList();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();

        try {
            boolean isRemoved = CustomerModel.delete(customerId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtCustomerId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtNumber.setText("");
                txtEmail.setText("");
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
        String customerId = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        Integer telNum = Integer.valueOf(txtNumber.getText());
        String email = txtEmail.getText();


        try {
            boolean isSaved = CustomerModel.save(new CustomerDTO(customerId, name,address, telNum, email));


            if (isSaved) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                txtCustomerId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtNumber.setText("");
                txtEmail.setText("");
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
        String customerId = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        Integer telNum = Integer.valueOf(txtNumber.getText());
        String email = txtEmail.getText();

        boolean isUpdated = false;
        try {
            isUpdated = CustomerModel.update(new CustomerDTO(customerId, name, address,telNum,email));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtCustomerId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtNumber.setText("");
                txtEmail.setText("");
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
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/viewCustomerForm.fxml"));
        customerPane.getChildren().clear();
        customerPane.getChildren().add(load);
    }

    @FXML
    void customerBtnOnAction(ActionEvent event) {

    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void homeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void itemBtnOnAction(ActionEvent event) {

    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) {

    }

    @FXML
    void orderBtnOnAction(ActionEvent event) {

    }

    @FXML
    void paymentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdSearchOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();

        try {
            CustomerDTO customerDTO= CustomerModel.search(customerId);

            if (customerDTO != null) {
                txtCustomerId.setText(customerDTO.getCustomerId());
                txtName.setText(customerDTO.getName());
                txtAddress.setText(customerDTO.getAddress());
                txtNumber.setText(String.valueOf(customerDTO.getTelNum()));
                txtEmail.setText(customerDTO.getEmail());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
