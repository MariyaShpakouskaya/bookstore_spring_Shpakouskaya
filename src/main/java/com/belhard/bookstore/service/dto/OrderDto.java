package com.belhard.bookstore.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderDto {
    private Long id;
    private UserDto userDto;
    private BigDecimal totalCost;
    private LocalDateTime timestamp;
    private Status status;
    private List<OrderItemDto> orderItemDtos;

    public OrderDto() {
    }

    public OrderDto(UserDto userDto, BigDecimal totalCost, LocalDateTime timestamp, Status status, List<OrderItemDto> orderItemDtos) {
        this.userDto = userDto;
        this.totalCost = totalCost;
        this.timestamp = timestamp;
        this.status = status;
        this.orderItemDtos = orderItemDtos;
    }

    public enum Status {
        CREATED,
        PROCESSED,
        COMPLETED,
        CANCELED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderItemDto> getOrderItemDtos() {
        return orderItemDtos;
    }

    public void setOrderItemDtos(List<OrderItemDto> orderItemDtos) {
        this.orderItemDtos = orderItemDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(userDto, orderDto.userDto) && Objects.equals(totalCost, orderDto.totalCost) && Objects.equals(timestamp, orderDto.timestamp) && status == orderDto.status && Objects.equals(orderItemDtos, orderDto.orderItemDtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userDto, totalCost, timestamp, status, orderItemDtos);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", userDto=" + userDto +
                ", totalCost=" + totalCost +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", orderItemDtos=" + orderItemDtos +
                '}';
    }
}
