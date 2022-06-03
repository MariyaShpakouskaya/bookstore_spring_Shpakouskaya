package com.belhard.bookstore.dao.mapper;

import com.belhard.bookstore.dao.entity.OrderItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getLong("id"));
//        orderItem.setOrder_id(resultSet.getLong("order_id"));
//        orderItem.setBook_id(resultSet.getLong("book_id"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.setPrice(resultSet.getBigDecimal("price"));
        return orderItem;
    }
}
