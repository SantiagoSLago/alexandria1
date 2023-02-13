
package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.Enums.Roles;
import com.lumbersoft.alexandria.entidades.Usuario;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class UsuarioService implements UserDetailsService{
    
    
    @Autowired
    private RepositorioUsuario urpo;
    
    
    @Transactional
    public void crearUsuario(String nombre, String email, String password1, String password2){
        
        mostrarData(nombre, email, password1, password2);
        
        
        try {
            validacionDatos(nombre, email, password1, password2);
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(new BCryptPasswordEncoder().encode(password1));
            usuario.setRol(Roles.USER);//User por defecto
            
            urpo.save(usuario);
            
        } catch (AlfaException e) {
            System.out.println(e.getMessage());//Control de la excepcion por consola (borrar luego)
            
        }
        
        
    }
    
    
    
    public void validacionDatos(String nombre, String email, String password1, String password2)throws AlfaException{
        
        
        
        if (nombre.isEmpty() || nombre.equals(" ")) {
            throw new AlfaException("El nombre no puede estar vacio ni ser nulo");
        }
        if (email.isEmpty() || email.equals(" ")) {
            throw new AlfaException("El email no puede estar vacio ni ser nulo");
        }
        if (password1.isEmpty() || password1.equals(" ")) {
            throw new AlfaException("El password no puede estar vacio ni ser nulo");
        }
        if (password2.isEmpty() || password2.equals(" ")) {
            throw new AlfaException("El password no puede estar vacio ni ser nulo");
        }

        if (!password1.equals(password2)) {
            throw new AlfaException("Las contraseñas deben ser identicas");
        }
        
        
        
    }
    
    
    
    
    
    
    
    public void mostrarData(String nombre, String email, String password1, String password2) {
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Passeord1: " + password1);
        System.out.println("Password2: " + password2);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = urpo.buscarPorMail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);
            //---De la session---
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession sesion = attr.getRequest().getSession(true);
            
            sesion.setAttribute("usuariosession", usuario);
            
            //------------------

            User user = new User(usuario.getEmail(), usuario.getPassword(), permisos);

            return user;
        } else {
            return null;
        }

    }
    
    public Usuario buscarPorid(String idUsuario) {

        //El metodo esta simple solo para laburar con este programa    
        return urpo.findById(idUsuario).get();
    }

    public List listadoUsuarios() {
        List<Usuario> usuarios = urpo.findAll();

        return usuarios;

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}//------------ENDO OF CLASS-------------------
