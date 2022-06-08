package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.OrderDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAll(Pageable pageable);

    OrderDto getById(Long id);

    OrderDto create(OrderDto orderDto);

    OrderDto update(OrderDto orderDto);

    void delete(Long id);

    int countAll();
}
