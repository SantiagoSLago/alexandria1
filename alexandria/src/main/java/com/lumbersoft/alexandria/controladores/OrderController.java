package com.lumbersoft.alexandria.controladores;


import com.lumbersoft.alexandria.entidades.Menu.Coffee;
import com.lumbersoft.alexandria.entidades.Menu.Purchase;
import com.lumbersoft.alexandria.entidades.DTO.PedidoRequestDTO;
import com.lumbersoft.alexandria.entidades.Menu.Sweets;
import com.lumbersoft.alexandria.servicios.CafeService;
import com.lumbersoft.alexandria.servicios.PedidoService;
import com.lumbersoft.alexandria.servicios.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class OrderController {


    @Autowired
    PedidoService pedidoService;

    @Autowired
    SweetService sweetService;

    @Autowired
    CafeService cafeService;



    @PostMapping("/altaPedido")
    @ResponseBody
    public ResponseEntity<Purchase> altaPedido(@RequestBody PedidoRequestDTO pedido) throws NoSuchObjectException {
        return new ResponseEntity<>(pedidoService.altaPedido(pedido), HttpStatus.CREATED);
    }



    @GetMapping("/pedidoExitoso/{id}")
    public String pedidoExitoso(@PathVariable Integer id, ModelMap modelo) throws NoSuchObjectException {

        Purchase purchase = pedidoService.buscarPorId(id);
        List<Coffee> coffees = purchase.getCoffees();
        List<Sweets> sweets = purchase.getSweets();

        modelo.addAttribute("purchase", purchase);
        modelo.addAttribute("coffees", coffees);
        modelo.addAttribute("sweets",sweets);

        return "Order/orderSuccess";
    }




    @GetMapping("/purchaseByCoffee/{id}")
    @ResponseBody
    public ResponseEntity<List<Purchase>> purchaseByCoffee(@PathVariable Integer id){

        List<Purchase> purchases = pedidoService.purchaseByCoffee(id);
        if(purchases.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        return new ResponseEntity<>(purchases,HttpStatus.OK);
    }

    @GetMapping("/purchaseBySweets/{id}")
    @ResponseBody
    public ResponseEntity<List<Purchase>> purchaseBySweets(@PathVariable Integer id){

        List<Purchase> purchases = pedidoService.purchaseByCoffee(id);
        if(purchases.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(purchases,HttpStatus.OK);
    }




    @GetMapping("/consultaPedido/{id}")
    public String consultaPedido(@PathVariable Integer id, ModelMap modelo) throws NoSuchObjectException {

        Purchase purchase = pedidoService.buscarPorId(id);
        List<Coffee> coffees = purchase.getCoffees();
        List<Sweets> sweets = purchase.getSweets();
        modelo.addAttribute("purchase", purchase);
        modelo.addAttribute("coffees", coffees);
        modelo.addAttribute("sweets",sweets);
        return "Order/purchase";
    }



    @GetMapping("/admin/listar")
    public String pedidos(ModelMap modelo){

        List<Purchase> purchases = pedidoService.listarPedidos();

        modelo.addAttribute("purchases", purchases);
        return "Order/allOrders";

    }








}
