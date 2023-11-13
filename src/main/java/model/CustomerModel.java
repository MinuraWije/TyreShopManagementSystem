package model;

import db.DBConnection;
import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerModel {
    public boolean saveCustomer(CustomerDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Insert into customer values (?,?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCustomerId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setInt(4,dto.getTelNum());
        pstm.setString(5, dto.getEmail());

        return pstm.executeUpdate()>0;
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Update customer set name=?, address=?, telNum=?, email=? where customerId=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setInt(3,dto.getTelNum());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getCustomerId());

        return pstm.executeUpdate()>0;
    }

    public boolean deleteCustomer(String customerId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Delete from customer where customerId =?";

        PreparedStatement pstm= connection.prepareStatement(sql);

        pstm.setString(1,customerId);
        return pstm.executeUpdate()>0;
    }
}
