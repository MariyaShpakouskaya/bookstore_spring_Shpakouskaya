package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.OrderItemDao;
import com.belhard.bookstore.dao.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderItemDaoImpl implements OrderItemDao {

    public static final String GET_ALL = "from OrderItem where deleted = false";
    public static final String GET_ALL_BY_ORDER_ID = "from OrderItem where order_id = ?";

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public OrderItemDaoImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public OrderItem getById(Long id) {
        OrderItem orderItem = manager.find(OrderItem.class, id);
        return orderItem;
    }

    @Override
    public List<OrderItem> getAll() {
        List<OrderItem> orderItems = manager.createQuery(GET_ALL, OrderItem.class).getResultList();
        return orderItems;
    }

    @Override
    public List<OrderItem> getByOrderId(Long order_id) {
        List<OrderItem> orderItems = manager.createQuery(GET_ALL_BY_ORDER_ID).getResultList();
        return orderItems;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        manager.persist(orderItem);
        return orderItem;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        manager.merge(orderItem);
        return orderItem;
    }

    @Override
    public boolean delete(Long id) {
        OrderItem managed = manager.find(OrderItem.class, id);
        boolean success = false;
        if (managed != null) {
            managed.setDeleted(true);
            manager.merge(managed);
            success = true;
        }
        return success;
    }
}
