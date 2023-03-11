
package com.lumbersoft.alexandria.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/error")
public class ErrorControlador {
    
    @GetMapping("/msjerror")
    public String errorMensaje(ModelMap modelo, String error){
        
        modelo.put("msgError", error);
        
        
        return "errorpage.html";
    }
 
    
    
    
    
}
