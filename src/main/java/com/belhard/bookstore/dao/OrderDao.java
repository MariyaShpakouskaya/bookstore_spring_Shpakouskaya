package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.Order;

import java.util.List;

public interface OrderDao {

    Order getById(Long id);

    List<Order> getAll();

    Order create(Order order);

    Order update(Order order);

    boolean delete(Long id);
}
