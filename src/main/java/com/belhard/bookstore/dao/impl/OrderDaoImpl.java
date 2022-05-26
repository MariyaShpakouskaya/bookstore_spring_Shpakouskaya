package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.OrderDao;
import com.belhard.bookstore.dao.mapper.OrderRowMapper;
import com.belhard.bookstore.dao.entity.Order;
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

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    public static final String GET_ALL = "SELECT o.id, o.user_id, o.timestamp, o.totalCost, s.status AS status FROM orders o JOIN status s ON o.status_id = s.id";
    public static final String GET_BY_ID = "SELECT o.id, o.user_id, o.timestamp, o.totalCost, s.status AS status FROM orders o JOIN status s ON o.status_id = s.id WHERE s.id = :id AND deleted = false";
    public static final String INSERT = "INSERT INTO orders (user_id, timestamp, totalCost, status_id) VALUES (:user_id, :timestamp, :totalCost, (SELECT id FROM orders WHERE name = :status))";
    public static final String UPDATE = "UPDATE orders SET user_id = :user_id, timestamp = :timestamp, totalCost = :totalCost, status_id = (SELECT id FROM orders WHERE name = :status) WHERE id = :id AND deleted = false";
    public static final String DELETE = "UPDATE orders SET deleted = true WHERE id = :id AND deleted = false";

    private final NamedParameterJdbcTemplate template;
    private final OrderRowMapper rowMapper;

    @Autowired
    public OrderDaoImpl(NamedParameterJdbcTemplate template, OrderRowMapper rowMapper) {
        this.template = template;
        this.rowMapper = rowMapper;
    }


    @Override
    public List<Order> getAll() {
        return template.query(GET_ALL, rowMapper);
    }

    @Override
    public Order getById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForObject(GET_BY_ID, params, rowMapper);
    }

    @Override
    public Order create(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", order.getUser_id());
        params.put("timestamp", order.getTimestamp());
        params.put("totalCost", order.getTotalCost());
        params.put("status", order.getStatus().toString().toLowerCase());
        SqlParameterSource source = new MapSqlParameterSource(params);
        int rowsUpdated = template.update(INSERT, source, keyHolder, new String[]{"id"});
        if (rowsUpdated != 1) {
            throw new RuntimeException("Can't create order!");
        }
        Long id = Optional.ofNullable(keyHolder.getKey()).map(Number::longValue)
                .orElseThrow(() -> new RuntimeException("Can't create order!"));
        return getById(id);
    }


    @Override
    public Order update(Order order) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", order.getUser_id());
        params.put("timestamp", order.getTimestamp());
        params.put("totalCost", order.getTotalCost());
        params.put("status", order.getStatus().toString().toLowerCase());
        SqlParameterSource source = new MapSqlParameterSource(params);
        int rowsUpdated = template.update(UPDATE, source);
        if (rowsUpdated != 1) {
            throw new RuntimeException("Can't create order!");
        }
        return getById(order.getId());
    }

    @Override
    public boolean delete(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.update(DELETE, params) == 1;
    }
}
