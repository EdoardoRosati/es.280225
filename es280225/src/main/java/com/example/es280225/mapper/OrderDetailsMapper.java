package com.example.es280225.mapper;

import com.example.es280225.object.dto.OrderDetailsDTO;
import com.example.es280225.object.model.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
    OrderDetailsMapper INSTANCE = Mappers.getMapper(OrderDetailsMapper.class);

    OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetails);
    OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO);
}
