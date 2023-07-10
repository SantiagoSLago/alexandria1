package com.lumbersoft.alexandria.servicios;


import com.lumbersoft.alexandria.entidades.Menu.Purchase;
import com.lumbersoft.alexandria.entidades.DTO.PedidoRequestDTO;
import com.lumbersoft.alexandria.entidades.Menu.Coffee;
import com.lumbersoft.alexandria.entidades.Menu.Sweets;
import com.lumbersoft.alexandria.repositorios.RepositorioPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.rmi.NoSuchObjectException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    RepositorioPedido pedidoRepo;

    @Autowired
    CafeService cafeService;

    @Autowired
    SweetService sweetService;


    @Transactional
    public Purchase altaPedido(PedidoRequestDTO pedidoRequestDTO) throws NoSuchObjectException {


        Purchase purchase = new Purchase();
        List<Coffee> coffees = new ArrayList<>();
        List<Sweets> sweets = new ArrayList<>();

        for (Integer i: pedidoRequestDTO.getArreglo_cafes()){
            coffees.add(cafeService.buscarCafePorId(i));
        }

        for(Integer i: pedidoRequestDTO.getArreglo_sweets()){
            sweets.add(sweetService.buscarPorId(i));
        }


        purchase.setCoffees(coffees);
        purchase.setSweets(sweets);
        purchase.setNumero_mesa(pedidoRequestDTO.getNumero_mesa());
        purchase.setPrecio_total(pedidoRequestDTO.getMonto_orden());
        purchase.setFecha(LocalDateTime.now());

        return pedidoRepo.save(purchase);


    }


    public Purchase buscarPorId(Integer id) throws NoSuchObjectException{

        Optional<Purchase> optional = pedidoRepo.findById(id);

        if(optional.isPresent()){
            return optional.get();
        } else{
            throw new NoSuchObjectException("El pedido no se encuentra registrado");
        }




    }

    public List<Purchase> listarPedidos(){
        return pedidoRepo.findAll();
    }


    public String eliminarPedido(Integer id) throws NoSuchObjectException {
        pedidoRepo.delete(buscarPorId(id));
        return "Purchase eliminado con exito";
    }

    public List<Purchase> purchaseByCoffee(Integer id){
        return pedidoRepo.purchaseByCoffee(id);
    }

    public List<Purchase> purchaseBySweets(Integer id){
        return pedidoRepo.purchaseBySweets(id);
    }






}
