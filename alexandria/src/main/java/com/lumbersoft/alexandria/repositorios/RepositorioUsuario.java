
package com.lumbersoft.alexandria.repositorios;

import com.lumbersoft.alexandria.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario,String> {
    
    
    @Query("Select u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorMail(String email);
    
}
