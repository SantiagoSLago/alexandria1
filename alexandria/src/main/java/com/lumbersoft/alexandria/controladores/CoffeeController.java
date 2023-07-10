package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.DTO.CafeRequestDTO;
import com.lumbersoft.alexandria.entidades.Menu.Coffee;
import com.lumbersoft.alexandria.excepciones.ValidacionException;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Objects;

import com.lumbersoft.alexandria.servicios.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/cafes")
public class CoffeeController {

    @Autowired
    private CafeService cafeService;


    @GetMapping("/listaCafes")
    public String listaCafe(ModelMap modelo) {

        List<Coffee> coffees = cafeService.listarCafes();

        modelo.addAttribute("coffees", coffees);

        return "Cafe/cafe";

    }


    @GetMapping("/availableCoffee")
    @ResponseBody
    public ResponseEntity<List<Coffee>> availableCoffee() {
        return new ResponseEntity<>(cafeService.allAvailableCoffee(), HttpStatus.OK);
    }

    @GetMapping("/unavailableCoffee")
    @ResponseBody
    private ResponseEntity<List<Coffee>> unavailableCoffe() {
        return new ResponseEntity<>(cafeService.allUnavailableCoffee(), HttpStatus.OK);
    }


    @PutMapping("/updateCoffee/{id}")
    @ResponseBody
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Integer id,@RequestBody CafeRequestDTO coffee) throws NoSuchObjectException {

        return new ResponseEntity<>(cafeService.updateCoffee(id,coffee),HttpStatus.OK);


    }

    @PutMapping("/updateState/{id}")
    @ResponseBody
    private ResponseEntity updateCoffeeState(@PathVariable Integer id) throws NoSuchObjectException {
        cafeService.setCoffeState(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/crearCafe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Coffee> crearCafe(@ModelAttribute CafeRequestDTO cafe, @RequestParam("file") MultipartFile img) throws ValidacionException {
        return new ResponseEntity<>(cafeService.crearCafe(cafe, img), HttpStatus.OK);
    }


    @DeleteMapping("/eliminarCafe/{id}")
    @ResponseBody
    public ResponseEntity<String> eliminarCafe(@PathVariable Integer id) throws NoSuchObjectException {
        return new ResponseEntity<>(cafeService.eliminarCafe(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Coffee> buscarCafePorID(@PathVariable Integer id, ModelMap modelo) throws NoSuchObjectException {
        return new ResponseEntity<>(cafeService.buscarCafePorId(id), HttpStatus.OK);
    }



}
