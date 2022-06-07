package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.dto.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAll(Model model, @RequestParam int page) {
        int size = 10;
        String sortColumn = "id";
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, sortColumn);
        List<OrderDto> orderDtos = orderService.getAll(pageable);
        model.addAttribute("orders", orderDtos);
        return "orders";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        OrderDto orderDto = orderService.getById(id);
        List<OrderItemDto> orderItemDtos = orderDto.getOrderItemDtos();
        model.addAttribute("order", orderDto);
        model.addAttribute("items", orderItemDtos);
        return "order";
    }
}
