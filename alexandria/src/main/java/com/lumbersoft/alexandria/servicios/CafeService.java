package com.lumbersoft.alexandria.servicios;


import com.lumbersoft.alexandria.entidades.DTO.CafeRequestDTO;
import com.lumbersoft.alexandria.entidades.Menu.Coffee;
import com.lumbersoft.alexandria.entidades.Menu.Purchase;
import com.lumbersoft.alexandria.excepciones.ValidacionException;
import com.lumbersoft.alexandria.repositorios.RepositorioCafe;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.NoSuchObjectException;
import java.util.*;

import com.lumbersoft.alexandria.repositorios.RepositorioPedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CafeService {

    private static final Logger LOGGER = Logger.getLogger(CafeService.class);

    @Autowired
    private RepositorioCafe caferep;

    @Autowired
    private RepositorioPedido pedidoRepo;


    @Transactional
    public Coffee crearCafe(CafeRequestDTO cafeRequest, MultipartFile img) throws ValidacionException {

        validacionData(cafeRequest);
        String imgName = uploadImageToFile(img);
        Coffee coffee = conversorDto(cafeRequest);
        coffee.setState(true);
        coffee.setImg(imgName);
        caferep.save(coffee);
        LOGGER.info("Coffe created: " + coffee.getNombre() + "/Price: " + coffee.getPrecio() + "/Measure: " + coffee.getMedida());

        return coffee;
    }


    public List<Coffee> listarCafes() {
        return caferep.findAll();
    }

    public List<Coffee> allAvailableCoffee() {
        List<Coffee> coffees = caferep.findAll();
        coffees.removeIf(c -> !c.isState());
        return coffees;
    }

    public List<Coffee> allUnavailableCoffee() {
        List<Coffee> coffees = caferep.findAll();
        coffees.removeIf(c -> c.isState());
        return coffees;
    }

    public List<Coffee> listaDeCafesPorId(List<Integer> id_cafes) throws NoSuchObjectException {

        List<Coffee> coffees = new ArrayList<>();
        for (Integer i : id_cafes) {
            coffees.add(buscarCafePorId(i));
        }

        return coffees;

    }


    public Coffee buscarCafePorId(Integer id) throws NoSuchObjectException {

        Optional<Coffee> respuesta = caferep.findById(id);

        if (respuesta.isEmpty()) {
            throw new NoSuchObjectException("No se ha encontrado el cafe solicitado");
        } else {
            Coffee coffee = respuesta.get();
            return coffee;
        }
    }


    @Transactional
    public Coffee updateCoffee(Integer id, CafeRequestDTO coffee) throws NoSuchObjectException {
        Coffee c = buscarCafePorId(id);

        c.setNombre(coffee.getNombre());
        c.setMedida(coffee.getMedida());
        c.setPrecio(coffee.getPrecio());
        caferep.save(c);

        LOGGER.info("Coffee updated: " + c.getNombre() + "/Price" + c.getPrecio() + "/Measure" + c.getMedida());
        return c;


    }


    @Transactional
    public void eliminarCafe(Integer id) throws NoSuchObjectException {
        Coffee coffee = buscarCafePorId(id);
        Integer coffeId = id;
        List<Purchase> purchases = pedidoRepo.purchaseByCoffee(id);
        List<Integer> purchasesId = new ArrayList<>();


        for (Purchase p : purchases) {
            purchasesId.add(p.getId());
            p.getCoffees().removeIf(c -> Objects.equals(c.getId(), id));
        }

        for (Integer i : purchasesId) {
            LOGGER.info("Order id deleted: " + i);
        }

        caferep.delete(coffee);
        LOGGER.info("Coffee id deleted: " + id);

    }

    @Transactional
    public void setCoffeState(Integer id) throws NoSuchObjectException {
        Coffee coffee = buscarCafePorId(id);

        coffee.setState(!coffee.isState());

        LOGGER.info("Status updated: " + coffee.getNombre() + "/" + coffee.isState());


    }


    public void validacionData(CafeRequestDTO cafe) throws ValidacionException {

        if (cafe.getNombre().isBlank()) {
            throw new ValidacionException("El nombre del cafe no debe ser nulo ni debe estar vacio");
        }

        if (cafe.getPrecio() == null) {
            throw new ValidacionException("El precio del cafe no debe ser nulo ni debe estar vacio");
        }
        if (cafe.getMedida() == null) {
            throw new ValidacionException("La medida del cafe no debe ser nula ni debe estar vacia");
        }


    }

    public Coffee conversorDto(CafeRequestDTO cafeDto) {

        Coffee coffee = new Coffee();
        coffee.setNombre(cafeDto.getNombre());
        coffee.setPrecio(cafeDto.getPrecio());
        coffee.setMedida(cafeDto.getMedida());

        return coffee;


    }

    public String uploadImageToFile(MultipartFile img) {

        Path directorioRecursos = Paths.get("src//main//resources//static//img//coffee");
        String roothPath = directorioRecursos.toFile().getAbsolutePath();

        if (!img.isEmpty()) {

            try {
                byte[] bytes = img.getBytes();
                Path rutaCompleta = Paths.get(roothPath + "//" + img.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                LOGGER.info("Imagen guardada con exito" + rutaCompleta.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "src//main//resources//static//uploads//img//coffee" + img.getOriginalFilename();
    }


}
