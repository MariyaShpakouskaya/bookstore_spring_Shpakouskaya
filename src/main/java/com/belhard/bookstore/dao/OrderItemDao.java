package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    OrderItem getById(Long id);

    List<OrderItem> getAll();

    List<OrderItem> getByOrderId(Long id);

    OrderItem create(OrderItem orderItem);

    OrderItem update(OrderItem orderItem);

    boolean delete(Long id);
}
