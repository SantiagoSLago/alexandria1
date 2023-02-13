
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEditorial extends JpaRepository<Editorial,Integer>{
    
}
