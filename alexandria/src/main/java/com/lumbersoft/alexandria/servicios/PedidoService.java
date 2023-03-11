package com.lumbersoft.alexandria.servicios;

import com.lumbersoft.alexandria.Enums.EstadoMesas;
import com.lumbersoft.alexandria.Enums.EstadoPedidos;
import com.lumbersoft.alexandria.entidades.Cafe;
import com.lumbersoft.alexandria.entidades.Cliente;
import com.lumbersoft.alexandria.entidades.Libro;
import com.lumbersoft.alexandria.entidades.Mesa;
import com.lumbersoft.alexandria.entidades.Pedido;
import com.lumbersoft.alexandria.entidades.UbicacionMesa;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.repositorios.RepositorioCafe;
import com.lumbersoft.alexandria.repositorios.RepositorioCliente;
import com.lumbersoft.alexandria.repositorios.RepositorioLibro;
import com.lumbersoft.alexandria.repositorios.RepositorioMesa;
import com.lumbersoft.alexandria.repositorios.RepositorioPedido;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private RepositorioPedido pro;

    @Autowired
    private LibroService ls;

    @Autowired
    private RepositorioCafe cr;

    @Autowired
    private CafeService cafcs;

    @Autowired
    private MesaService ms;

    @Autowired
    private ClienteService cs;

    @Autowired
    private UbicacionMesaService ums;

    //----------METHODS------------//
    //>>Transactional Methods<<
    @Transactional
    public void generarPedido(Integer idCafe, Long isbn, Integer idMesa, String nombreCliente, String apellidoCliente) throws AlfaException {

        validacionPedido(idCafe, isbn, idMesa, nombreCliente, apellidoCliente);
        Pedido pedido = new Pedido();

        Cafe cafe = cafcs.buscarCafePorId(idCafe);
        
        Libro libro = ls.buscarPorIsbn(isbn);
        ls.chequearStockLibro(libro);
        ls.disminuirStockLibro(libro);
        
        Mesa mesa = ms.buscarPorNumero(idMesa);
        ms.ocupar_desocupar_mesa(mesa.getNumero());
        
        

        

        Cliente cliente = cs.crearCliente(nombreCliente, apellidoCliente);

        pedido.setCafe(cafe);
        pedido.setCliente(cliente);
        pedido.setLibro(libro);
        pedido.setMesa(mesa);
        pedido.setEstado(EstadoPedidos.ORDENADO);
        //mostrarPedido(pedido);

        pro.save(pedido);

    }

    @Transactional
    public void eliminarPedido(Integer id) throws AlfaException {
       Pedido pedido;
        Optional<Pedido> opcion = pro.findById(id);
        if(opcion.isPresent()){
            pedido =opcion.get();
            if(pedido.getMesa().getEstado_mesa().equals(EstadoMesas.OCUPADA)){
                 ms.ocupar_desocupar_mesa(buscarPorId(id).getMesa().getNumero());
            }
            
        }
               
        pro.delete(pro.findById(id).get());
        
        
    }

    @Transactional
    public void modificarPedido(Integer id, String nombre, String apellido, Long isbn, Integer idCafe, Integer numeroMesa) throws AlfaException {

        Pedido pedido = buscarPorId(id);

        try {
            //Armar un buscar cliente por apellido por si haty que modificar el cliente (Proximo Sprint)
            pedido.setLibro(ls.buscarPorIsbn(isbn));
            pedido.setCafe(cafcs.buscarCafePorId(idCafe));
            pedido.setMesa(ms.buscarPorNumero(numeroMesa));
            pro.save(pedido);

        } catch (Exception e) {
            System.out.println("Problemas en la modificacion del pedido");
        }

    }

    @Transactional
    public void cambiarEstadoPedido(Integer idPedido) throws AlfaException {

        Pedido pedido = buscarPorId(idPedido);

        switch (pedido.getEstado()) {
            case ORDENADO:
                pedido.setEstado(EstadoPedidos.PREPARANDO);
                break;
            case PREPARANDO:
                pedido.setEstado(EstadoPedidos.COMPLETADO);
                break;
            case COMPLETADO:
                pedido.setEstado(EstadoPedidos.PAGADO);
                ls.aumentarStockLibro(pedido.getLibro().getIsbn());
                ms.ocupar_desocupar_mesa(pedido.getMesa().getNumero());
                break;
            case PAGADO:
                throw new AlfaException("El pedido ya se encuentra pagado y finalizado");

            default:
                throw new AssertionError();
        }
    }

    @Transactional
    public void anularPedido(Integer idPedido) throws AlfaException {
        Pedido pedido = buscarPorId(idPedido);
        pedido.setEstado(EstadoPedidos.ANULADO);
    }

    //>>Common Mehods<<
    public void mostrarPedido(Pedido pedido) {
        System.out.println(pedido.getCliente().getNombre());
        System.out.println(pedido.getCafe().getNombre());
        System.out.println(pedido.getMesa().getUbicacion());
        System.out.println(pedido.getLibro().getTitulo());

    }

    public List<Pedido> listarPedidos() {

        return pro.findAll();

    }

    public Pedido buscarPorId(Integer id) throws AlfaException {

        try {
            Optional<Pedido> resp = pro.findById(id);
            if (!resp.isPresent()) {
                throw new AlfaException("No se encontro el pedido solicitado");

            } else {
                Pedido pedido = resp.get();
                return pedido;
            }
        } catch (AlfaException e) {
            e.getMessage();
            return null;
        }

    }

    public void validacionPedido(Integer idCafe, Long isbn, Integer idMesa, String nombreCliente, String apellidoCliente) throws AlfaException {

        if (nombreCliente == null || nombreCliente.isEmpty()) {
            throw new AlfaException("Por favor introduzca un nombre valido");
        } else if (apellidoCliente == null || apellidoCliente.isEmpty()) {
            throw new AlfaException("Por favor introduzca un apellido valido");
        } else if (isbn == null) {
            throw new AlfaException("Por favor seleccione un libro");
        } else if (idCafe == null) {
            throw new AlfaException("Por favor seleccione un tipo de cafe");
        } else if (idMesa == null) {
            throw new AlfaException("Por favor seleccione un numero de Mesa");
        }

    }

}
