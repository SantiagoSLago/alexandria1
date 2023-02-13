package com.lumbersoft.alexandria.entidades;

import com.lumbersoft.alexandria.Enums.EstadoPedidos;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne
    private Cafe cafe;
    
    @OneToOne
    private Libro libro;
    @OneToOne
    private Mesa mesa;
    @OneToOne
    private Cliente cliente;
    
    @Enumerated(EnumType.STRING)
    private EstadoPedidos estado;

    public Pedido() {
    }

    public EstadoPedidos getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidos estado) {
        this.estado = estado;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    

}
