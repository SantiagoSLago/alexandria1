package com.lumbersoft.alexandria.controladores;


import com.lumbersoft.alexandria.entidades.Menu.Coffee;
import com.lumbersoft.alexandria.entidades.Menu.Sweets;
import com.lumbersoft.alexandria.servicios.CafeService;
import com.lumbersoft.alexandria.servicios.SweetService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    CafeService cafeService;


    @Autowired
    SweetService sweetService;

    @PermitAll
    @GetMapping("/ver")
    public String renderizarMenu(ModelMap modelo) {
        List<Coffee> coffees = cafeService.listarCafes();
        List<Sweets> sweets = sweetService.listarSweets();

        modelo.put("coffees", coffees);
        modelo.put("sweets", sweets);
        return "menu";


    }


}
