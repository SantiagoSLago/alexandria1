package com.lumbersoft.alexandria.repositorios;


import com.lumbersoft.alexandria.entidades.SystemEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RepositorioUser extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

}
