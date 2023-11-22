package model;

import db.DBConnection;
import dto.PlaceOrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    private  OrderModel orderModel = new OrderModel();
    private  ItemModel itemModel = new ItemModel();
    public boolean placeOrder(PlaceOrderDTO placeOrderDto) throws SQLException {
        //System.out.println(placeOrderDto);

        String orderId = placeOrderDto.getOrderId();
        //double amount = placeOrderDto.getAmount();
        String customerId = placeOrderDto.getCustomerId();
        LocalDate pickupDate = placeOrderDto.getPickupDate();

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved =  orderModel.save(orderId, customerId,pickupDate);
            if (isOrderSaved) {
                boolean isUpdated = itemModel.updateItem(placeOrderDto.getCartTmList());
                if (isUpdated) {
                    connection.commit();
                }
            }
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
