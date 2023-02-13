
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
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    
    @Autowired
    private UsuarioService us;
    
    //PostMapping solo para registrar Usuario
    @PostMapping("/registrarUsuario")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String email, 
            @RequestParam String password1, @RequestParam String password2, ModelMap modelo){
        
        
        try {
            
            us.crearUsuario(nombre, email, password1, password2);
            
            return "login.html";
            
        } catch (Exception e) {
            //Manejar la Excepcion por aca y sacarla del service, despues.
            return "signIn.html";
        }
        
        
        
        
    }
    
    
    
    
    
//    @GetMapping("/login")
//    public String login(@RequestParam(required = false)String error,ModelMap modelo) {
//        
//        if(error != null){
//           modelo.put("error", "Contraseña o usurio invalidos");
//        }
//        
//        
//        return "login.html";
//    }
    
    
    
    
    
    
    
    
    
}
