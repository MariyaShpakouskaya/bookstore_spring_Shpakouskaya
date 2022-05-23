package com.belhard.bookstore.dao;

import java.util.List;

public interface OrderDao {

    OrderDao getById(Long id);

    List<OrderDao> getAll();

    OrderDao create(OrderDao orderDao);

    OrderDao update(OrderDao orderDao);

    boolean delete(Long id);
}
