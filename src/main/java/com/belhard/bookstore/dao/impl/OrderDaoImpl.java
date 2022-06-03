package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.OrderDao;
import com.belhard.bookstore.dao.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    public static final String GET_ALL = "from Order where deleted = false";

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public OrderDaoImpl(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public List<Order> getAll() {
        List<Order> orders = manager.createQuery(GET_ALL, Order.class).getResultList();
        return orders;
    }

    @Override
    public Order getById(Long id) {
        try {
            Order order = manager.find(Order.class, id);
            return order;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public Order create(Order order) {
        manager.persist(order);
        return order;
    }


    @Override
    public Order update(Order order) {
        manager.merge(order);
        return order;
    }

    @Override
    public boolean delete(Long id) {
        Order managed = manager.find(Order.class, id);
        boolean success = false;
        if (managed != null) {
            managed.setDeleted(true);
            manager.merge(managed);
            success = true;
        }
        return success;
    }
}
