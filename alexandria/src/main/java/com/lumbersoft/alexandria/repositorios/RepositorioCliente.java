
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, Integer> {
    
}
