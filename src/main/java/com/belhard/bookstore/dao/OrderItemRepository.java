package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> getOrderItemsByOrderId(Long id);
}
