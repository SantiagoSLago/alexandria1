package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Cliente;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.servicios.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class ClienteControlador {

    @Autowired
    private ClienteService cs;

    @GetMapping("/clienteinit")
    public String crearCliente(ModelMap modelo) {

        List<Cliente> clientes = cs.listarClientes();

        modelo.addAttribute("clientes", clientes);

        return "cliente.html";

    }

    @PostMapping("/crearCliente")
    public String crearCliente(@RequestParam String nombre, @RequestParam String apellido) throws AlfaException {

        cs.crearCliente(nombre, apellido);

        return "cliente.html";

    }

    @GetMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Integer id, ModelMap modelo) {

        try {
            cs.eliminarCliente(id);
            List<Cliente> clientes = cs.listarClientes();
            modelo.addAttribute("clientes", clientes);
            return "cliente.html";
        } catch (AlfaException e) {
            e.getMessage();
            return "errorpage.html";
            
        }

    }

}
