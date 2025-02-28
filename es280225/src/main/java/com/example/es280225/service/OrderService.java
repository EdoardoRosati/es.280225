package com.example.es280225.service;

import com.example.es280225.mapper.OrderMapper;
import com.example.es280225.object.dto.OrderDTO;
import com.example.es280225.object.dto.ProductDTO;
import com.example.es280225.object.model.Order;
import com.example.es280225.object.model.Product;
import com.example.es280225.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return orderMapper.toOrderDTO(order);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toOrder(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.toOrderDTO(order);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        existingOrder.setData(orderDTO.getData());
        existingOrder.setStato(String.valueOf(orderDTO.getData()));
        existingOrder.setTotale(orderDTO.getTotale());

        existingOrder = orderRepository.save(existingOrder);
        return orderMapper.toOrderDTO(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    public Double getTotalByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .mapToDouble(Order::getTotale)
                .sum();
    }

    public List<OrderDTO> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByDataBetween(startDate, endDate).stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }
}
