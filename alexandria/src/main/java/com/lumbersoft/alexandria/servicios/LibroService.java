package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.entidades.Autor;
import com.lumbersoft.alexandria.entidades.Editorial;
import com.lumbersoft.alexandria.entidades.Libro;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioAutor;
import com.lumbersoft.alexandria.repositorios.RepositorioEditorial;
import com.lumbersoft.alexandria.repositorios.RepositorioLibro;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private RepositorioLibro lrpo;
    @Autowired
    private RepositorioAutor arpo;
    @Autowired
    private RepositorioEditorial erpo;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, Integer idAutor, Integer idEditorial) throws AlfaException {

        validacion(isbn, titulo, ejemplares, idAutor, idEditorial); //Metodo de validacion

        Autor autor = arpo.findById(idAutor).get();

        Editorial editorial = erpo.findById(idEditorial).get();

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        lrpo.save(libro);
    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();

        libros = lrpo.findAll();

        return libros;

    }

    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer idAutor, Integer idEditorial, Integer ejemplares) throws AlfaException {

        Optional<Libro> respuesta = lrpo.findById(isbn);
        Optional<Autor> aut = arpo.findById(idAutor);
        Optional<Editorial> edit = erpo.findById(idEditorial);

        if (!respuesta.isPresent()) {
            throw new AlfaException("No se ha encontrado el libro solicitado");
        } else if (!aut.isPresent()) {
            System.out.println("No se ha encontrado el autor");
            throw new AlfaException("No se ha encontrado el autor solicitado");

        } else if (!edit.isPresent()) {
            throw new AlfaException("No se ha encontrado la editorial solicitada");
        } else {
            Libro libro = respuesta.get();
            Autor autor = aut.get();
            Editorial editorial = edit.get();

            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setIsbn(isbn);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            lrpo.save(libro);
        }

    }

    public Libro buscarPorIsbn(Long isbn) throws AlfaException {
        try {
            Optional<Libro> resp = lrpo.findById(isbn);
            if (!resp.isPresent()) {
                throw new AlfaException("El libro no ha sido encontrado");
            } else {
                Libro libro = resp.get();
                return libro;
            }
        } catch (AlfaException e) {
            e.getMessage();
            return null;
        }

    }

    @Transactional
    public void eliminarLibro(Long isbn) throws AlfaException {

        lrpo.delete(buscarPorIsbn(isbn));

    }

    public boolean chequearStockLibro(Libro libro) throws AlfaException {

        if (libro.getEjemplares() > 0) {

            return true;
        } else {
            throw new AlfaException("No quedan ejemplares disponibles del libro seleccionado");

        }
    }

    @Transactional
    public void disminuirStockLibro(Libro libro) {
        libro.setEjemplares(libro.getEjemplares()-1);
    }
    
    @Transactional
    public void aumentarStockLibro(Long isbn) throws AlfaException{
        Libro libro = buscarPorIsbn(isbn);
        libro.setEjemplares(libro.getEjemplares()+1);
    }

    private void validacion(Long isbn, String titulo, Integer ejemplares, Integer idAutor, Integer idEditorial) throws AlfaException {

        if (titulo == null || titulo.isEmpty()) {
            throw new AlfaException("Debe ingresarse un valor por Titulo");
        } else if (isbn == null) {
            throw new AlfaException("Debe ingresarse un valor por ISBN");
        } else if (ejemplares == null) {
            throw new AlfaException("Debe ingresarse un valor por Ejemplares");
        } else if (idAutor == null) {
            throw new AlfaException("Debe ingresarse un valor por el ID del Autor");
        } else if (idEditorial == null) {
            throw new AlfaException("Debe ingresarse un valor el ID de la Editorial");
        }

    }

}
