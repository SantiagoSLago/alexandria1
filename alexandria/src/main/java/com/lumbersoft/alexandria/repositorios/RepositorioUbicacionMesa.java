
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.UbicacionMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUbicacionMesa extends JpaRepository<UbicacionMesa, Integer> {
    
}
