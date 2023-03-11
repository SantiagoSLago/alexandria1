package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Autor;
import com.lumbersoft.alexandria.entidades.Editorial;
import com.lumbersoft.alexandria.entidades.Libro;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.servicios.AutorService;
import com.lumbersoft.alexandria.servicios.EditorialService;
import com.lumbersoft.alexandria.servicios.LibroService;
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
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroService ls;

    @Autowired
    private AutorService as;

    @Autowired
    private EditorialService es;

    @GetMapping("/libroing")
    public String libroInit(ModelMap modelo) {

        List<Autor> autores = as.listarAutores();
        List<Editorial> editoriales = es.listarEditorial();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro.html";

    }

    @PostMapping("/libroreg")
    public String libroReg(@RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo, @RequestParam(required = false) Integer ejemplares,
            @RequestParam(required = false) Integer idAutor, @RequestParam(required = false) Integer idEditorial, ModelMap modelo) {
        try {
            ls.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

            modelo.put("msgExito", "El libro ha sido guardado con exito");
            return "libro.html";

        } catch (Exception ex) {
            modelo.put("msgError", ex.getMessage());

            return "libro.html";

        }

    }

    @GetMapping("/libroinit")
    public String listaLibros(ModelMap modelo) {

        List<Libro> libros = ls.listarLibros();

        modelo.put("libros", libros);

        return "librolist.html";

    }

    @GetMapping("/modificarlibro/{isbn}")
    public String modificarLibro(@PathVariable Long isbn, ModelMap modelo) throws AlfaException {

        modelo.put("libro", ls.buscarPorIsbn(isbn));
        List<Autor> autores = as.listarAutores();
        List<Editorial> editoriales = es.listarEditorial();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libromodif.html";

    }

    @PostMapping("/modificarlibro/{isbn}")
    public String modificarLibro(@PathVariable Long isbn, String titulo, Integer ejemplares, Integer idAutor, Integer idEditorial, ModelMap modelo) throws AlfaException {

        try {
            
            
            ls.modificarLibro(isbn, titulo, idAutor, idEditorial, ejemplares);
            List<Libro> libros = ls.listarLibros();

        modelo.put("libros", libros);
            return "librolist.html";
            
        } catch (Exception e) {
            
            e.printStackTrace();
            System.out.println("Error en la modificacion del libro");
            return "errorpage.html";
        }

    }

    @GetMapping("/eliminarLibro/{isbn}")
    public String eliminarLibro(@PathVariable Long isbn, ModelMap modelo) throws AlfaException{
        
       
        ls.eliminarLibro(isbn);
        List<Libro> libros = ls.listarLibros();

        modelo.put("libros", libros);
        
        return "librolist.html";
    }
    
    
   
}
