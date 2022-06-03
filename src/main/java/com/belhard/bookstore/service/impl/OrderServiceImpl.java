package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.dao.OrderDao;
import com.belhard.bookstore.dao.OrderItemDao;
import com.belhard.bookstore.dao.entity.Order;
import com.belhard.bookstore.dao.entity.OrderItem;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.dto.OrderItemDto;
import com.belhard.bookstore.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final UserService userService;
    private final BookService bookService;

    public OrderServiceImpl(OrderDao orderDao, OrderItemDao orderItemDao, UserService userService, BookService bookService) {
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public List<OrderDto> getAll() {
        return orderDao.getAll().stream().map(this::mapToDto).toList();
    }

    @Override
    public OrderDto getById(Long id) {
        Order order = orderDao.getById(id);
        return mapToDto(order);
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        orderDto.setTotalCost(calculateOrderCost(orderDto));
        Order entity = mapToEntity(orderDto);
        orderDao.create(entity);
        List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            orderItemDao.create(mapToItemEntity(orderDto.getId(), orderItemDto));
        }
        return getById(orderDto.getId());
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        orderDto.setTotalCost(calculateOrderCost(orderDto));
        Order entity = mapToEntity(orderDto);
        orderDao.update(entity);
        List<OrderItem> orderItems = orderItemDao.getByOrderId(orderDto.getId());
        for (OrderItem orderItem : orderItems) {
            orderItemDao.delete(orderItem.getId());
        }
        List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            orderItemDao.create(mapToItemEntity(orderDto.getId(), orderItemDto));
        }
        return getById(orderDto.getId());
    }

    @Override
    public void delete(Long id) {
        List<OrderItem> orderItems = orderItemDao.getByOrderId(id);
        for (OrderItem orderItem : orderItems) {
            orderItemDao.delete(orderItem.getId());
        }
        orderDao.delete(id);
    }

    private BigDecimal calculateOrderCost(OrderDto orderDto) {
        List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();
        BigDecimal totalCost = BigDecimal.ZERO;
        for (OrderItemDto orderItemDto : orderItemDtos) {
            BigDecimal itemCost = orderItemDto.getPrice().multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));
            totalCost = totalCost.add(itemCost);
        }
        return totalCost;
    }

    private Order mapToEntity(OrderDto orderDto) {
        Order entity = new Order();
        entity.setId(orderDto.getId());
        entity.setTotalCost(orderDto.getTotalCost());
        entity.setTimestamp(orderDto.getTimestamp());
        entity.setStatus(Order.Status.valueOf(orderDto.getStatus().toString()));
        User user = userService.userDtoToUser(orderDto.getUserDto());
        entity.setUser(user);
        List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            orderItems.add(mapToItemEntity(orderDto.getId(), orderItemDto));
        }
        return entity;
    }

    private OrderItem mapToItemEntity(Long orderId, OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setBook(bookService.bookDtoToBook(orderItemDto.getBookDto()));
        return orderItem;
    }

    private OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalCost(order.getTotalCost());
        orderDto.setTimestamp(order.getTimestamp());
        orderDto.setStatus(OrderDto.Status.valueOf(order.getStatus().toString()));
        UserDto userDto = userService.userToUserDto(order.getUser());
        orderDto.setUserDto(userDto);
        List<OrderItem> orderItems = orderItemDao.getByOrderId(order.getId());
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            orderItemDtos.add(mapOrderItemToDto(orderItem));
        }
        orderDto.setOrderItemDtos(orderItemDtos);
        return orderDto;
    }

    private OrderItemDto mapOrderItemToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());
        BookDto bookDto = bookService.bookToBookDto(orderItem.getBook());
        orderItemDto.setBookDto(bookDto);
        return orderItemDto;
    }

}
