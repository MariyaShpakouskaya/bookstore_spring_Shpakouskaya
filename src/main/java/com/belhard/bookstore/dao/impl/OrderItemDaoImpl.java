package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.OrderItemDao;
import com.belhard.bookstore.dao.mapper.OrderItemRowMapper;
import com.belhard.bookstore.dao.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("orderItemDao")

public class OrderItemDaoImpl implements OrderItemDao {

    public static final String GET_ALL = "SELECT id, order_id, book_id, quantity, price FROM items_order";
    public static final String GET_BY_ID = "SELECT id, order_id, book_id, quantity, price FROM items_order WHERE id = :id AND deleted = false";
    private static final String GET_BY_ORDER_ID = "SELECT id, order_id, book_id, quantity, price FROM items_order WHERE order_id = :order_id AND deleted = false";
    public static final String INSERT = "INSERT INTO items_order (order_id, book_id, quantity,  price) VALUES (:order_id, :book_id, :quantity, :price)";
    public static final String UPDATE = "UPDATE items_order SET order_id = :order_id, book_id = :book_id, quantity = :quantity, price = :price WHERE id = :id AND deleted = false";
    public static final String DELETE = "UPDATE items_order SET deleted = true WHERE id = :id AND deleted = false";


    private final NamedParameterJdbcTemplate template;
    private final OrderItemRowMapper rowMapper;

    @Autowired
    public OrderItemDaoImpl(NamedParameterJdbcTemplate template, OrderItemRowMapper rowMapper) {
        this.template = template;
        this.rowMapper = rowMapper;
    }

    @Override
    public OrderItem getById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForObject(GET_BY_ID, params, rowMapper);
    }

    @Override
    public List<OrderItem> getAll() {
        return template.query(GET_ALL, rowMapper);
    }

    @Override
    public List<OrderItem> getByOrderId(Long order_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", order_id);
        return template.query(GET_BY_ORDER_ID, params, rowMapper);
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", orderItem.getOrder_id());
        params.put("book_id", orderItem.getBook_id());
        params.put("quantity", orderItem.getQuantity());
        params.put("price", orderItem.getPrice());
        SqlParameterSource source = new MapSqlParameterSource(params);
        int rowsUpdated = template.update(INSERT, source, keyHolder, new String[]{"id"});
        if (rowsUpdated != 1) {
            throw new RuntimeException("Can't add item order!");
        }
        Long id = Optional.ofNullable(keyHolder.getKey()).map(Number::longValue)
                .orElseThrow(() -> new RuntimeException("Can't add item order!"));
        return getById(id);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", orderItem.getOrder_id());
        params.put("book_id", orderItem.getBook_id());
        params.put("quantity", orderItem.getQuantity());
        params.put("price", orderItem.getPrice());
        SqlParameterSource source = new MapSqlParameterSource(params);
        int rowsUpdated = template.update(UPDATE, source);
        if (rowsUpdated != 1) {
            throw new RuntimeException("Can't add item order!");
        }
        return getById(orderItem.getId());
    }

    @Override
    public boolean delete(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.update(DELETE, params) == 1;
    }
}
