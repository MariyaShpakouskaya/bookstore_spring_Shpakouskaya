package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    long countOrdersByDeletedFalse();
}
