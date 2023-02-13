
package com.lumbersoft.alexandria.entidades;

import com.lumbersoft.alexandria.Enums.EstadoMesas;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

        

@Entity
public class Mesa {
 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;
    
    @OneToOne
    private UbicacionMesa ubicacion;
    
    @Enumerated(EnumType.STRING)
    private EstadoMesas estado_mesa;
    

    public Mesa() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public UbicacionMesa getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionMesa ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoMesas getEstado_mesa() {
        return estado_mesa;
    }

    public void setEstado_mesa(EstadoMesas estado_mesa) {
        this.estado_mesa = estado_mesa;
    }
    
    

    
   

    
    
    
    
    
    
    
}
