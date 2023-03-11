package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Autor;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.servicios.AutorService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorService as;

    @GetMapping("/autoresInicio")
    public String registrar(ModelMap modelo) throws AlfaException {
        List<Autor> autores = as.listarAutores();
        
        modelo.addAttribute("autores", autores);
        
        
        return "listadoAutores.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {

        try {
            as.crearAutor(nombre);
            modelo.put("msgExito", "Autor registrado con exito");
            List<Autor> autores = as.listarAutores();

            modelo.addAttribute("autores", autores);
        } catch (AlfaException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("msgError", ex.getMessage());
            List<Autor> autores = as.listarAutores();

            modelo.addAttribute("autores", autores);
            return "listadoAutores.html"; 
        }

        return "listadoAutores.html";//modificado, antes iba a autorform
    }

    @GetMapping("/registrar")
    public String listarAutores(ModelMap modelo, @RequestParam("valorPrueba")String valorPrueba) {
        List<Autor> autores = as.listarAutores();
        if(valorPrueba != null){
            modelo.put("msjPrueba", "activar");
        }

        modelo.addAttribute("autores", autores);

        return "listadoAutores.html";//Aca modifique

    }

    @GetMapping("/modificar/{id}")
    public String modificarAutor(@PathVariable Integer id, ModelMap modelo) throws AlfaException {

        List<Autor> autores = as.listarAutores();

        modelo.addAttribute("autores", autores);

        modelo.put("autor", as.buscarporId(id));

        return "autormodif.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificarAutor(@PathVariable Integer id, @RequestParam(required = true) String nombre, ModelMap modelo) throws AlfaException {//por si llega a fallar algo prestar atencion al requested param, suele saltar por ahi

        try {
            Autor autor_modif = as.buscarporId(id);
            if (nombre.isEmpty()) {
                throw new AlfaException("El campo modificar debe no debe estar en blanco");

            } else {
                autor_modif.setNombre(nombre);

                as.modificarAutor(autor_modif);
                List<Autor> autores = as.listarAutores();
                modelo.addAttribute("autores", autores);
                return "listadoAutores.html";
            }

        } catch (AlfaException e) {

            modelo.put("errorMsg", "El campo modificar debe no debe estar en blaco");
            System.out.println(e.getMessage());
            return "errorpage.html";

        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Integer id, ModelMap modelo) throws AlfaException {

        try {
            
            Autor autor_elim = as.buscarporId(id);
            as.eliminarAutor(autor_elim);
            List<Autor> autores = as.listarAutores();
            modelo.put("autores", autores);
            return "listadoAutores.html";
            
        } catch (AlfaException a) {
            
            System.out.println(a.getMessage());
            return "errorpage.html";

        }

    }

}
