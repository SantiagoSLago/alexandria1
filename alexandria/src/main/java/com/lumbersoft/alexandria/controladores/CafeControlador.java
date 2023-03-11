package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Cafe;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioCafe;
import com.lumbersoft.alexandria.servicios.CafeService;
import java.util.ArrayList;
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
@RequestMapping("/cafe")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class CafeControlador {

    @Autowired
    private CafeService cs;

    @GetMapping("/cafeinit")
    public String crearCafe(ModelMap modelo) {

        List<Cafe> cafes = cs.listarCafes();

        modelo.addAttribute("cafes", cafes);

        return "cafe.html";

    }

    @PostMapping("/crearCafe")
    public String crearCafe(String nombre, ModelMap modelo) {

        try {
            cs.crearCafe(nombre);
            modelo.put("msgExito", "Cafe cargado con exito");
            List<Cafe> cafes = cs.listarCafes();

            modelo.addAttribute("cafes", cafes);
            return "cafe.html";

        } catch (AlfaException ex) {
            modelo.put("msgError", ex.getMessage());
            return "cafe.html";
        }

    }
    
    
    
    @GetMapping("/modificar/{id}")
    public String modificarCafe(@PathVariable Integer id,ModelMap modelo) throws AlfaException{
        List<Cafe> cafes = cs.listarCafes();
        
        modelo.addAttribute("cafes",cafes);
        modelo.put("cafe", cs.buscarCafePorId(id));
        
        
        return "cafeModif.html";
    }
    
    
    @PostMapping("/modificar/{id}")
    public String modificarCafe(@PathVariable Integer id,@RequestParam(required = true) String nombre, ModelMap modelo ){
        
        try{
            Cafe cafe_modif = cs.buscarCafePorId(id);
            if( nombre.isEmpty() || nombre == null){
                throw new AlfaException("El nombre no puede estar vacio");
            }else{
                cafe_modif.setNombre(nombre);
                cs.modificarCafe(cafe_modif);
                List<Cafe> cafes = cs.listarCafes();
                modelo.addAttribute("cafes",cafes);
                return "cafe.html";
                
            }
        }catch (AlfaException ex){
            modelo.put("msgError", "El campo a modificar no puede estar vacio");
            System.out.println(ex.getMessage());
            return "cafeModif.html";//Agregar las tarjetas de error y de exito.
        }
        
        
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCafe(@PathVariable Integer id, ModelMap modelo) {

        try {
            Cafe cafe_elim = cs.buscarCafePorId(id);
            cs.eliminarCafe(cafe_elim);
            List<Cafe> cafes = cs.listarCafes();
            modelo.put("cafes", cafes);
            return "cafe.html";
        } catch (AlfaException ex) {
            System.out.println(ex.getMessage());
            return "errorpage.html";
        }

    }

}
