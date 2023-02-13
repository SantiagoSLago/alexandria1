
package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.entidades.UbicacionMesa;
import com.lumbersoft.alexandria.repositorios.RepositorioUbicacionMesa;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionMesaService {
    
    @Autowired
    private RepositorioUbicacionMesa umrpo;
    
    
    @Transactional
    public void agregarUbicacion(String ubicacion){
        UbicacionMesa ubi = new UbicacionMesa();
        
        ubi.setNombre(ubicacion);
        
        umrpo.save(ubi);
        
        
    }
    
    public List<UbicacionMesa> listarUbicaciones(){
        
        List<UbicacionMesa> ubicaciones = new ArrayList();
        
        ubicaciones = umrpo.findAll();
        return ubicaciones;
        
        
        
    }
    
    public UbicacionMesa buscarPorId(Integer id){
        
        return umrpo.findById(id).get();
        
    }
    
    
}
