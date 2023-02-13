
package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Autor;
import com.lumbersoft.alexandria.entidades.Mesa;
import com.lumbersoft.alexandria.entidades.UbicacionMesa;
import com.lumbersoft.alexandria.servicios.AutorService;
import com.lumbersoft.alexandria.servicios.MesaService;
import com.lumbersoft.alexandria.servicios.UbicacionMesaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/mesa")
public class MesaControlador {
    
    
    @Autowired
    private MesaService ms;
    
    @Autowired
    private UbicacionMesaService ums;
    
    @Autowired
    private AutorService as;
    
    
    @GetMapping("/mesainit")
    public String crearMesa(ModelMap modelo){
        
        
        List<UbicacionMesa> ubis = ums.listarUbicaciones();
        List<Mesa> mesas = ms.listarMesas();
        
        
        
        modelo.addAttribute("ubicaciones", ubis);
        modelo.addAttribute("mesas", mesas);
       
        return "mesa.html";
        
    }
    
    
    @PostMapping("/crearMesa")
    public String crearMesa(Integer idUbicacion,ModelMap modelo){
        
       
        ms.crearMesa(idUbicacion);
        
        List<UbicacionMesa> ubis = ums.listarUbicaciones();
        
        
        modelo.addAttribute("ubicaciones", ubis);
        return "mesa.html";
        
        
    }
    
    
    
}
