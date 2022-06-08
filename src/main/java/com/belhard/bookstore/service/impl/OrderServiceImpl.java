package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.dao.BookRepository;
import com.belhard.bookstore.dao.OrderItemRepository;
import com.belhard.bookstore.dao.OrderRepository;
import com.belhard.bookstore.dao.UserRepository;
import com.belhard.bookstore.dao.entity.Order;
import com.belhard.bookstore.dao.entity.OrderItem;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.dto.OrderItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final BookService bookService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserRepository userRepository, UserService userService, BookRepository bookRepository, BookService bookService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Override
    public List<OrderDto> getAll(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = mapToDto(order);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.getById(id);
        return mapToDto(order);
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        orderDto.setTotalCost(calculateOrderCost(orderDto));
        Order entity = mapToEntity(orderDto);
        orderRepository.save(entity);
        List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            orderItemRepository.save(mapToItemEntity(orderItemDto));
        }
        return getById(orderDto.getId());
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        orderDto.setTotalCost(calculateOrderCost(orderDto));
        Order entity = mapToEntity(orderDto);
        orderRepository.save(entity);
        List<OrderItem> orderItems = orderItemRepository.getOrderItemsByOrderId(orderDto.getId());
        for (OrderItem orderItem : orderItems) {
            orderItem = orderItemRepository.getById(orderItem.getId());
            orderItem.setDeleted(true);
            orderItemRepository.save(orderItem);
        }
        List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();
        for (OrderItemDto orderItemDto : orderItemDtos) {
            orderItemRepository.save(mapToItemEntity(orderItemDto));
        }
        return getById(orderDto.getId());
    }

    @Override
    public void delete(Long id) {
        List<OrderItem> orderItems = orderItemRepository.getOrderItemsByOrderId(id);
        for (OrderItem orderItem : orderItems) {
            orderItem = orderItemRepository.getById(orderItem.getId());
            orderItem.setDeleted(true);
            orderItemRepository.save(orderItem);
        }
        Order order = orderRepository.getById(id);
        order.setDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public int countAll() {
        return (int) orderRepository.count();
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
        entity.setUser(userRepository.getById(orderDto.getUserDto().getId()));
        entity.setTotalCost(orderDto.getTotalCost());
        entity.setStatus(Order.Status.valueOf(orderDto.getStatus().toString()));
        entity.setTimestamp(orderDto.getTimestamp());
        return entity;
    }

    private OrderItem mapToItemEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrder(orderRepository.getById(orderItemDto.getOrderId()));
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setBook(bookRepository.getById(orderItemDto.getBookDto().getId()));
        return orderItem;
    }

    private OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserDto(userService.getUserById(order.getUser().getId()));
        orderDto.setTotalCost(order.getTotalCost());
        orderDto.setTimestamp(order.getTimestamp());
        orderDto.setStatus(OrderDto.Status.valueOf(order.getStatus().toString().toUpperCase()));
        List<OrderItem> orderItems = orderItemRepository.getOrderItemsByOrderId(order.getId());
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
        orderItemDto.setBookDto(bookService.getBookById(orderItem.getBook().getId()));
        return orderItemDto;
    }

}
