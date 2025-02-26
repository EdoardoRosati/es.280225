package com.example.es280225.repository;

import com.example.es280225.object.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>  {

}
