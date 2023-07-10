package com.lumbersoft.alexandria.controladores;


import com.lumbersoft.alexandria.entidades.DTO.SweetRequestDTO;
import com.lumbersoft.alexandria.entidades.Menu.Sweets;
import com.lumbersoft.alexandria.excepciones.ValidacionException;
import com.lumbersoft.alexandria.servicios.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.List;

@Controller
@RequestMapping("/admin/sweets")
public class SweetsController {

    @Autowired
    SweetService sweetService;


    @PostMapping(value = "/crearSweet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Sweets> crearSweet(@ModelAttribute SweetRequestDTO sweet, @RequestParam("file") MultipartFile img) throws ValidacionException, IOException {
        return new ResponseEntity<>(sweetService.crearSweets(sweet,img),HttpStatus.OK);
    }

    @GetMapping("/listaSweets")
    public String listaSweets(ModelMap modelo){
        List<Sweets> sweets = sweetService.listarSweets();
        modelo.addAttribute("sweets",sweets);
        return "Sweets/sweets";
    }

    @PutMapping("/updateSweet/{id}")
    @ResponseBody
    public ResponseEntity<Sweets> updateSweet(@PathVariable Integer id,@RequestBody SweetRequestDTO sweet) throws NoSuchObjectException {
        return new ResponseEntity<>(sweetService.updateSweet(id,sweet),HttpStatus.OK);
    }

    @GetMapping("/availableSweets")
    @ResponseBody
    public ResponseEntity<List<Sweets>> availableSweets(){
        return new ResponseEntity<>(sweetService.allAvailableSweets(),HttpStatus.OK);
    }

    @GetMapping("/unavailableSweets")
    @ResponseBody
    public ResponseEntity<List<Sweets>> unavailableSweets(){
        return new ResponseEntity<>(sweetService.allUnavailableSweets(),HttpStatus.OK);
    }

    @DeleteMapping("eliminarSweet/{id}")
    public ResponseEntity<String> eliminarSweet(@PathVariable Integer id) throws NoSuchObjectException {
        return new ResponseEntity<>(sweetService.eliminarSweet(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sweets> searchSweetById(@PathVariable Integer id) throws NoSuchObjectException {
        return new ResponseEntity<>(sweetService.buscarPorId(id),HttpStatus.OK);
    }

    @PutMapping("/updateState/{id}")
    public ResponseEntity updateSweetState(@PathVariable Integer id) throws NoSuchObjectException {
        sweetService.setSweetState(id);
        return new ResponseEntity(HttpStatus.OK);
    }





}
