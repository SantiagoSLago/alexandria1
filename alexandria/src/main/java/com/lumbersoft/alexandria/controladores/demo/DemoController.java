package com.lumbersoft.alexandria.controladores.demo;


import com.lumbersoft.alexandria.entidades.Menu.Coffee;
import com.lumbersoft.alexandria.entidades.Menu.Sweets;
import com.lumbersoft.alexandria.servicios.CafeService;
import com.lumbersoft.alexandria.servicios.PedidoService;
import com.lumbersoft.alexandria.servicios.SweetService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



//-------------------CONTROLLER USED FOR TESTING------------------//
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    CafeService cafeService;


    @Autowired
    SweetService sweetService;

    @Autowired
    PedidoService pedidoService;




    @PermitAll
    @GetMapping("/menu")
    public String renderizarMenu(ModelMap modelo) {
        List<Coffee> coffees = cafeService.allAvailableCoffee();
        List<Sweets> sweets = sweetService.allAvailableSweets();



        modelo.put("coffees", coffees);
        modelo.put("sweets", sweets);
        return "nMenu";


    }


    @GetMapping("/demoSweets")
    public ResponseEntity<List<Sweets>> listarSweetsDemo(){
        return new ResponseEntity<>(sweetService.listarSweets(),HttpStatus.OK);
    }











}
