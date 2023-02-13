
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface RepositorioCafe extends JpaRepository<Cafe,Integer> {
    
}
