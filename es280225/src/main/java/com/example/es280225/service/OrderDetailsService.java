package com.example.es280225.service;

import com.example.es280225.mapper.OrderDetailsMapper;
import com.example.es280225.object.dto.OrderDetailsDTO;
import com.example.es280225.object.model.OrderDetails;
import com.example.es280225.repository.OrderDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;

    public List<OrderDetailsDTO> getAllOrderDetails() {
        return orderDetailsRepository.findAll().stream()
                .map(orderDetailsMapper::toOrderDetailsDTO)
                .collect(Collectors.toList());
    }

    public OrderDetailsDTO getOrderDetailsById(Long id) {
        return orderDetailsRepository.findById(id)
                .map(orderDetailsMapper::toOrderDetailsDTO)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    public OrderDetailsDTO createOrderDetails(OrderDetailsDTO orderDetailsDto) {
        OrderDetails order = orderDetailsMapper.toOrderDetails(orderDetailsDto);
        return orderDetailsMapper.toOrderDetailsDTO(orderDetailsRepository.save(order));
    }

    public OrderDetailsDTO updateOrderDetails(Long id, OrderDetailsDTO orderDetailsDto) {
        OrderDetails existingOrder = orderDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        existingOrder.setQuantita(orderDetailsDto.getQuantita());
        existingOrder.setPrezzoTotale(orderDetailsDto.getPrezzoTotale());
        return orderDetailsMapper.toOrderDetailsDTO(orderDetailsRepository.save(existingOrder));
    }

    public void deleteOrder(Long id) {
        if (!orderDetailsRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found");
        }
        orderDetailsRepository.deleteById(id);
    }
}

