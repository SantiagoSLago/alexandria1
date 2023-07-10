package com.lumbersoft.alexandria.repositorios;


import com.lumbersoft.alexandria.entidades.SecurityEntities.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities,Long> {
    Optional<Authorities> findByName(Authorities name);
}

