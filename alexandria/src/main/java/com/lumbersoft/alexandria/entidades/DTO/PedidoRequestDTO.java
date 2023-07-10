package com.lumbersoft.alexandria.entidades.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequestDTO {

    private List<Integer> arreglo_cafes;
    private List<Integer> arreglo_sweets;
    private Double monto_orden;
    private Integer numero_mesa;

}
