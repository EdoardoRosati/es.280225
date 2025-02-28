package com.example.es280225.repository;

import com.example.es280225.object.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {

    List<Order> findByUserId(Long userId);

    List<Order> findByDataBetween(LocalDate startDate, LocalDate endDate);

}
