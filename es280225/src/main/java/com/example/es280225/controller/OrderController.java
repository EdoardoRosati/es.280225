package com.example.es280225.controller;

import com.example.es280225.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.es280225.object.dto.OrderDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> getTotalByUserId(@PathVariable Long userId) {
        Double total = orderService.getTotalByUserId(userId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/between")
    public ResponseEntity<List<OrderDTO>> getOrdersBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<OrderDTO> orders = orderService.getOrdersBetweenDates(startDate, endDate);
        return ResponseEntity.ok(orders);
    }
}