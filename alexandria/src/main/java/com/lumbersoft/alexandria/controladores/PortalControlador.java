package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Cafe;
import com.lumbersoft.alexandria.entidades.Libro;
import com.lumbersoft.alexandria.entidades.Mesa;
import com.lumbersoft.alexandria.entidades.Pedido;
import com.lumbersoft.alexandria.entidades.Usuario;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import java.util.List;
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
@RequestMapping("/")
public class PortalControlador {

    @GetMapping("/")
    public String index() {
        return "login.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            modelo.put("error", "Contraseña o usuario invalidos");
        }

        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicioExitoso(ModelMap modelo, HttpSession session) {
        System.out.println("ENTRO");

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        System.out.println(logueado.getNombre());//control de data del usuario logueado

        //Validacion de Rol para redireccionar al template de admin
        if (logueado.getRol().toString().equals("ADMIN")) {
            //return "redirect:/admin/dashboard";
            return "home.html";
        }

        

        return "redirect:/pedido/pedidosUsuario";
    }

}
