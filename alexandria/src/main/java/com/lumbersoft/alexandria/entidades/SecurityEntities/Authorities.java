package com.lumbersoft.alexandria.entidades.SecurityEntities;


import com.lumbersoft.alexandria.Enums.NombreAutoridades;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "autorities")
@Getter
@Setter
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private NombreAutoridades name;



}
