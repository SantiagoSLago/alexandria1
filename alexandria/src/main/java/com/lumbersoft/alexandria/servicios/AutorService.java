
package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.entidades.Autor;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioAutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AutorService {
    
   @Autowired
   private RepositorioAutor arpo;
   
   @Transactional
   public void crearAutor(String nombre) throws AlfaException{
       Autor autor = new Autor();
       
       if(nombre == null || nombre.isEmpty()){
           throw new AlfaException("El nombre del autor a ingresar no puede ser nulo o estar vacio");    
       }
       
       autor.setNombre(nombre);
       
       
       arpo.save(autor);
       
   }
   
   
   public List<Autor> listarAutores(){
       try{
           List<Autor> autores = new ArrayList();
       autores = arpo.findAll();
       return autores;
       }catch (Exception e){
           e.printStackTrace();
           
           return null;
           
       }
       
       
       
   }
   
   public Autor buscarporId(Integer id)throws AlfaException{

           Optional<Autor> resp = arpo.findById(id);
           
           if(resp.isPresent()){
               Autor autor = resp.get();
               return autor;
           }else{
               throw new AlfaException("No se ha encontrado el autor");
               
           }
              
   }
   @Transactional
   public void modificarAutor(Autor autor){
       Autor cambio_autor= arpo.findById(autor.getId()).get();
       
       cambio_autor.setNombre(autor.getNombre());
       
       arpo.save(cambio_autor);
       
   }
   
   @Transactional
   public void eliminarAutor(Autor autor){
       
       Autor elim_autor= arpo.findById(autor.getId()).get();
       
       arpo.delete(elim_autor);
       
   }
    
}
