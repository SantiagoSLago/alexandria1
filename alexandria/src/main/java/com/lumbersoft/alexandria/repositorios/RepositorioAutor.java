
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAutor extends JpaRepository<Autor,Integer>{
    
}
