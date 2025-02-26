package com.example.es280225.object.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDate data;
    private String stato; // IN_ATTESA, SPEDITO, CONSEGNATO
    private Double totale;
    private List<OrderDetailsDTO> dettagli;
}
