
package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.entidades.Cafe;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioCafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CafeService {
    
    @Autowired
    private RepositorioCafe caferep;
    
    @Transactional
    public void crearCafe(String nombre) throws AlfaException{
        
        if(nombre == null || nombre.isEmpty()){
            throw new AlfaException("El nombre del cafe no puede ser nulo o vacio");
        }
        
        Cafe cafe = new Cafe();
        
        cafe.setNombre(nombre);
        
        caferep.save(cafe);
        
    }
    
    
    public List<Cafe> listarCafes(){
    
        List<Cafe> cafes = new ArrayList();
        
        cafes = caferep.findAll();
        return cafes;
        
        
    }
    
    
    public Cafe buscarCafePorId(Integer id) throws AlfaException{
        
        Optional<Cafe> respuesta = caferep.findById(id);
        
        if(!respuesta.isPresent()){
            throw new AlfaException("No se ha encontrado el cafe solicitado");
        }else{
            Cafe cafe = respuesta.get();
            return cafe;
        }
    }
    
    
    @Transactional
    public void modificarCafe(Cafe cafe){
        Cafe cafe_modif = caferep.findById(cafe.getId()).get();
        
        cafe_modif.setNombre(cafe.getNombre());
        caferep.save(cafe_modif);
        
        
        
    }
    
    
    @Transactional
    public void eliminarCafe(Cafe cafe){    
        
        // probar armarlo luego buscando el cafe por id y agregando la excepcion si no lo encuentra
        
        
        caferep.delete(cafe);
    }
    
    
}
