package com.example.es280225.mapper;

import com.example.es280225.object.dto.OrderDTO;
import com.example.es280225.object.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toOrderDTO(Order order);
    Order toOrder(OrderDTO orderDTO);
}