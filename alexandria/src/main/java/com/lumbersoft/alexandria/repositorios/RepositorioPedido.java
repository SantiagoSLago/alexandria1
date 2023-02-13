
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioPedido extends JpaRepository<Pedido,Integer>{
    
    
    
    
}
