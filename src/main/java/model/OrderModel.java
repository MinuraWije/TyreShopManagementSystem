package model;

import dto.OrderDTO;
import dto.tm.OrderTM;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public static OrderDTO search(String orderId) throws SQLException {
        String sql = "SELECT * FROM orders where orderId = ?";

        ResultSet resultSet = CrudUtil.execute(sql, orderId);

        if (resultSet.next()){
            OrderDTO orderDTO= new OrderDTO();
            orderDTO.setOrderId(resultSet.getString(1));
            orderDTO.setCustomerId(resultSet.getString(2));
            orderDTO.setOrderDate(resultSet.getDate(2).toLocalDate());

            return orderDTO;
        }
        return null;
    }

    public static List<OrderTM> getAll() throws SQLException {
        String sql = "SELECT * FROM orders";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<OrderTM> data = new ArrayList<>();
        while (resultSet.next()) {
            OrderTM orderTM = new OrderTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate()

            );
            data.add(orderTM);
        }
        return data;
    }

    public static boolean save(OrderDTO orderDTO) throws SQLException {
        String sql = "INSERT INTO orders(orderId,customerID,orderDate) VALUES(?,?,?)";
        boolean isSaved = CrudUtil.execute(sql, orderDTO.getOrderId(),orderDTO.getCustomerId(),orderDTO.getOrderDate());
        return isSaved;
    }

    public static boolean update(OrderDTO orderDTO) throws SQLException {
        String sql = "UPDATE orders set customerId=?,orderDate=? WHERE orderId = ?";
        return CrudUtil.execute(sql,orderDTO.getCustomerId(),orderDTO.getOrderDate(),orderDTO.getOrderId());
    }

    public static boolean delete(String orderId) throws SQLException {
        String sql = "DELETE FROM orders WHERE orderId = ?";
        return CrudUtil.execute(sql,orderId);
    }
}
