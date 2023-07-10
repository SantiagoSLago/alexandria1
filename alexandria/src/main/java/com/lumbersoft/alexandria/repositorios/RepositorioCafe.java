
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.Menu.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepositorioCafe extends JpaRepository<Coffee,Integer> {


    
}
