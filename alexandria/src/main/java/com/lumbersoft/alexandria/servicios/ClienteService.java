
package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.entidades.Cliente;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioCliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ClienteService {
    
    @Autowired
    private RepositorioCliente crpo;
    
    
    @Transactional 
    public Cliente crearCliente(String nombre, String apellido)throws AlfaException{
        //Hice que este metodo de persistencia devuelva un cliente porque vamos a necesitar el cliente creado a fines de asignarlo al pedido, cuando creemos el pedido
        Cliente cliente = new Cliente();
        
        if(nombre == null || nombre.isEmpty()){
            throw new AlfaException("Debe ingresar un nombre del cliente");
        }else if( apellido == null || apellido.isEmpty()){
            throw new AlfaException("Debe ingresar un apellido del cliente");
        }
        
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        
        crpo.save(cliente);
        
        return cliente;
    }
    
    
    public List<Cliente> listarClientes(){
        
        List<Cliente> clientes = new ArrayList();
        
        clientes = crpo.findAll();
        
        return clientes;
        
        
        
    }
    
    public Cliente buscarPorId(Integer id) throws AlfaException{
        
        Optional<Cliente> opCliente = crpo.findById(id);
        
        if(!opCliente.isPresent()){
            throw new AlfaException("No se ha encontrado el cliente que busca");
            
        }else{
            Cliente cliente = opCliente.get();
            return cliente;
        }
        
       
             
    }
    
    
    @Transactional
    public void eliminarCliente(Integer id) throws AlfaException{
        
        crpo.delete(buscarPorId(id));
    
}
    
    
    
}
