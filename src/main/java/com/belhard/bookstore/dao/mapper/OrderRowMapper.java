package com.belhard.bookstore.dao.mapper;

import com.belhard.bookstore.dao.entity.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setUser_id(resultSet.getLong("user_id"));
        order.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
        order.setTotalCost(resultSet.getBigDecimal("totalCost"));
        order.setStatus(Order.Status.valueOf(resultSet.getString("status")));
        return order;
    }
}
