package com.lumbersoft.alexandria.entidades.Menu;


import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public abstract class Product {


    private String nombre;
    private Double precio;
    private String img;
    private boolean state;













}
