package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Editorial;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.servicios.EditorialService;
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
@RequestMapping("/editorial")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class EditorialControlador {

    @Autowired
    private EditorialService es;

    @GetMapping("/listaeditorial")
    public String registrarEdit(ModelMap modelo) {

        List<Editorial> editoriales = es.listarEditorial();

        modelo.addAttribute("editoriales", editoriales);
        return "editolist.html";
    }

    @PostMapping("registroedit")
    public String registroEdit(@RequestParam("nombre") String nombre, ModelMap modelo) {

        try {
            es.crearEditorial(nombre);
            modelo.put("msgExito", "Editorial cargada con exito");
            List<Editorial> editoriales = es.listarEditorial();

            modelo.addAttribute("editoriales", editoriales);
        } catch (AlfaException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("msgError", ex.getMessage());
            List<Editorial> editoriales = es.listarEditorial();

            modelo.addAttribute("editoriales", editoriales);
            return "editolist.html";
        }

        return "editolist.html";//Cambio de editoform.html a editolist.html
    }

    @GetMapping("/registraredit")
    public String listarEdito(ModelMap modelo) {

        List<Editorial> editoriales = es.listarEditorial();

        modelo.addAttribute("editoriales", editoriales);

        return "editoform.html";

    }

    @GetMapping("modificaredit/{id}")
    public String modificarEdit(@PathVariable Integer id, ModelMap modelo) throws AlfaException {

        List<Editorial> editoriales = es.listarEditorial();

        modelo.addAttribute("editoriales", editoriales);

        modelo.put("editorial", es.buscarEditorialPorId(id));

        return "editomodif.html";

    }

    @PostMapping("modificaredit/{id}")
    public String modificarEdit(@PathVariable Integer id, @RequestParam(required = true) String nombre, ModelMap modelo) throws AlfaException {

        try {
            Editorial edit_modif = es.buscarEditorialPorId(id);
            if (nombre.isEmpty()) {
                throw new AlfaException("El campo modificar no debe estar en blanco");

            } else {
                edit_modif.setNombre(nombre);
                es.modificarEditorial(edit_modif);
                List<Editorial> editoriales = es.listarEditorial();
                modelo.addAttribute("editoriales", editoriales);
                return "editolist.html";
            }

        } catch (AlfaException e) {
            //Surge lo mismo que al modificar autor, ver la manera de hacer llegar el mensaje de error al template de error.

            modelo.put("errorMsg", "El campo modificar debe no debe estar en blaco");
            System.out.println(e.getMessage());

            return "errorpage.html";
        }

    }

    @GetMapping("eliminaredit/{id}")
    public String eliminarEditorial(@PathVariable Integer id, ModelMap modelo) throws AlfaException {

        try {
            
            Editorial edit_elim = es.buscarEditorialPorId(id);
            es.eliminarEditorial(edit_elim);
            List<Editorial> editoriales = es.listarEditorial();
            modelo.put("editoriales", editoriales);
            return "editolist.html";
            
        }catch (AlfaException e){
            
            System.out.println(e.getMessage());
            return "errorpage.html";
        }

        
    }

}
