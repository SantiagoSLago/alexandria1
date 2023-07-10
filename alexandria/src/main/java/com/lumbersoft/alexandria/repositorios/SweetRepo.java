package com.lumbersoft.alexandria.repositorios;


import com.lumbersoft.alexandria.entidades.Menu.Sweets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SweetRepo extends JpaRepository<Sweets,Integer> {
}
