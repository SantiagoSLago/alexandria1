package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.Enums.EstadoMesas;
import com.lumbersoft.alexandria.entidades.Mesa;
import com.lumbersoft.alexandria.entidades.UbicacionMesa;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioMesa;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaService {

    @Autowired
    private RepositorioMesa mrpo;

    @Autowired
    private UbicacionMesaService ums;

    @Transactional
    public void crearMesa(Integer idUbicacion) {

        Mesa mesa = new Mesa();
        mesa.setUbicacion(ums.buscarPorId(idUbicacion));
        mesa.setEstado_mesa(EstadoMesas.LIBRE);

        mrpo.save(mesa);

    }

    public List<Mesa> listarMesas() {

        List<Mesa> mesas = new ArrayList();

        mesas = mrpo.findAll();

        return mesas;

    }

    public Mesa buscarPorNumero(Integer numero) throws AlfaException {
        try {
            Optional<Mesa> resp = mrpo.findById(numero);

            if (!resp.isPresent()) {
                throw new AlfaException("No se ha encotnrado la mesa que busca");

            } else {
                Mesa mesa = resp.get();
                return mesa;
            }
        } catch (AlfaException e) {
            e.getMessage();
            return null;
        }

    }

    @Transactional
    public void reservar_liberar_mesa(Integer numero) throws AlfaException {
        
        Mesa mesa = buscarPorNumero(numero);
        
        switch (mesa.getEstado_mesa()) {
            case LIBRE:
                mesa.setEstado_mesa(EstadoMesas.RESERVADA);
                break;
            case RESERVADA:
                mesa.setEstado_mesa(EstadoMesas.LIBRE);
                break;
            default:

        }
    }
    
    @Transactional
    public void ocupar_desocupar_mesa(Integer numero) throws AlfaException{
        
        Mesa mesa = buscarPorNumero(numero);
        
        switch (mesa.getEstado_mesa()) {
            case LIBRE:
                mesa.setEstado_mesa(EstadoMesas.OCUPADA);
                break;
            case OCUPADA:
                mesa.setEstado_mesa(EstadoMesas.LIBRE);
              break;
            default:
                throw new AssertionError();
        }
    }
    
    
    @Transactional
    public void eliminar_mesa(Integer numero) throws AlfaException{
        mrpo.delete(buscarPorNumero(numero));
        
        
        
    }
    
    
    public List<Integer> listar_numeros_de_mesa(){
        
        List<Mesa> mesas = listarMesas();
        List<Integer> numeros_mesas = new ArrayList<>();
        
        for (Mesa mesa : mesas) {
            numeros_mesas.add(mesa.getNumero());
            
        }
        
        for (Integer num : numeros_mesas) {
            System.out.println("Numero: " + num);
        }
        
        return numeros_mesas;
        
    }
          

}
