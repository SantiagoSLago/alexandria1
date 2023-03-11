
package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Usuario;
import com.lumbersoft.alexandria.servicios.UsuarioService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    
    @Autowired
    private UsuarioService us;
    
    
    @GetMapping("/registrarUsuarioPage")
    public String registrarUsuario(){
        return "signIn.html";
    }
    
    //PostMapping solo para registrar Usuario
    @PostMapping("/registrarUsuario")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String email, 
            @RequestParam String password1, @RequestParam String password2, ModelMap modelo){
        
        
        try {
            
            us.crearUsuario(nombre, email, password1, password2);
            
            return "login.html";
            
        } catch (Exception e) {
            
           
            modelo.put("nombre",nombre);
            modelo.put("email",email);
            //Agregar cartel de mensaje de error en el sign in
            return "signIn.html";
        }
        
        
        
        
    }
    
    
    
    
    

    
    
    
    
    
    
    
    
    
}
