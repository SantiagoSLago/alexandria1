package com.lumbersoft.alexandria.entidades.Menu;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Purchase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numero_mesa;


    @ManyToMany
    private List<Coffee> coffees;


    @ManyToMany
    private List<Sweets> sweets;
    private Double precio_total;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd/MM/yyyy")
    private LocalDateTime fecha;


}
