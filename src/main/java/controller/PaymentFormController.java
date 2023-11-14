package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.OrderDTO;
import dto.PaymentDTO;
import dto.tm.OrderTM;
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
import model.OrderModel;
import model.PaymentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentFormController {

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
    private AnchorPane paymentPane;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXTextField txtPaymentId;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXComboBox<?> cmbOrderId;

    @FXML
    private JFXDatePicker paymentDate;

    @FXML
    private JFXTextField txtDescription;

    ObservableList<PaymentDTO> observableList = FXCollections.observableArrayList();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();

        try {
            boolean isRemoved = PaymentModel.delete(paymentId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtPaymentId.setText("");
                cmbOrderId.setValue("");
                txtAmount.setText("");
                paymentDate.setValue(LocalDate.parse(""));
                txtDescription.setText("");
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
        String paymentId = txtPaymentId.getText();
        String orderId = (String) cmbOrderId.getValue();
        Double amount = Double.parseDouble(txtAmount.getText());
        LocalDate date = paymentDate.getValue();
        String description = txtDescription.getText();


        try {
            boolean isSaved = PaymentModel.save(new PaymentDTO(paymentId, orderId,amount, date, description));


            if (isSaved) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                txtPaymentId.setText("");
                cmbOrderId.setValue("");
                txtAmount.setText("");
                paymentDate.setValue(LocalDate.parse(""));
                txtDescription.setText("");
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
        String paymentId = txtPaymentId.getText();
        String orderId = (String) cmbOrderId.getValue();
        Double amount = Double.parseDouble(txtAmount.getText());
        LocalDate date = paymentDate.getValue();
        String description = txtDescription.getText();

        boolean isUpdated = false;
        try {
            isUpdated = PaymentModel.update(new PaymentDTO(paymentId, orderId, amount,date,description));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtPaymentId.setText("");
                cmbOrderId.setValue("");
                txtAmount.setText("");
                paymentDate.setValue(LocalDate.parse(""));
                txtDescription.setText("");
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
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/viewPaymentForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }


    @FXML
    void customerBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employeeForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
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
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/orderForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void paymentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void txtPaymentIdSearchOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();

        try {
            PaymentDTO paymentDTO= PaymentModel.search(paymentId);

            if (paymentDTO != null) {
                txtPaymentId.setText(paymentDTO.getPaymentId());
                cmbOrderId.setValue(paymentDTO.getOrderId());
                txtAmount.setText(String.valueOf(paymentDTO.getAmount()));
                paymentDate.setValue(paymentDTO.getPaymentDate());
                txtDescription.setText(paymentDTO.getDescription());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbOrderIdOnAction(ActionEvent event) {
        String orderId = (String) cmbOrderId.getValue();

        try {
            OrderDTO orderDTO = OrderModel.search(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadOrderId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<OrderTM> orderTMS = OrderModel.getAll();

            for (OrderTM dto : orderTMS) {
                obList.add(dto.getOrderId());
            }
            cmbOrderId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        loadOrderId();
    }

}
