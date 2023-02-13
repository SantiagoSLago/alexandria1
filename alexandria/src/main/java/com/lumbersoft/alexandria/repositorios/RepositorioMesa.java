
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMesa extends JpaRepository<Mesa,Integer> {
    
}
