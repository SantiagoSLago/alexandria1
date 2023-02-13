
package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.entidades.Editorial;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioEditorial;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {
    
    @Autowired
    private RepositorioEditorial erpo;
    
    
    @Transactional
    public void crearEditorial(String nombre) throws AlfaException{
        
        
        if(nombre == null || nombre.isEmpty()){
            throw new AlfaException("El nombre de la editorial no puede ser nulo o estar vacio");
        }
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        erpo.save(editorial);
        
    }
    
    
    public List<Editorial> listarEditorial(){
        List<Editorial> editoriales = new ArrayList();
        
        editoriales = erpo.findAll();
        return editoriales;
        
        
        
    }
    
    
    public Editorial buscarEditorialPorId(Integer id)throws AlfaException{
       
            Optional<Editorial> respuesta = erpo.findById(id);
            
            if(!respuesta.isPresent()){
                throw new AlfaException("No se ha encontrado la editorial");
            }else{
               Editorial editorial = respuesta.get();
               return editorial;
            }
        
        
        
        
        
        
    }
    
    @Transactional
    public void modificarEditorial(Editorial editorial){
        
    Editorial edito_modif = erpo.findById(editorial.getId()).get();
    
     edito_modif.setNombre(editorial.getNombre());
     
     erpo.save(edito_modif);
     
        
        
    }
    
    @Transactional //Esta armado diferente al de eliminar autor, chequear si asi funciona, si es asi modificar el de autor
    public void eliminarEditorial(Editorial editorial){
       
        erpo.delete(editorial);
    }
    
    
    
    
}


