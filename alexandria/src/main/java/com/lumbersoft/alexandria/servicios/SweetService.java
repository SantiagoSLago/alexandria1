package com.lumbersoft.alexandria.servicios;


import com.lumbersoft.alexandria.entidades.DTO.SweetRequestDTO;
import com.lumbersoft.alexandria.entidades.Menu.Purchase;
import com.lumbersoft.alexandria.entidades.Menu.Sweets;
import com.lumbersoft.alexandria.excepciones.ValidacionException;
import com.lumbersoft.alexandria.repositorios.RepositorioPedido;
import com.lumbersoft.alexandria.repositorios.SweetRepo;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SweetService {


    @Autowired
    SweetRepo sweetRepo;

    @Autowired
    RepositorioPedido pedidoRepo;


    private static final Logger LOGGER = Logger.getLogger(CafeService.class);


    @Transactional
    public Sweets crearSweets(SweetRequestDTO sweetRequest, MultipartFile img) throws ValidacionException {

        validacionData(sweetRequest);
        String imgName = uploadImageToFile(img);
        Sweets sweet = conversorDTO(sweetRequest);
        sweet.setState(true);
        sweet.setImg(imgName);
        sweetRepo.save(sweet);

        LOGGER.info("Sweet created: " + sweet.getNombre() + "/Price: "+ sweet.getPeso() + "/Measure: " + sweet.getPeso());

        return sweet;
    }

    @Transactional
    public Sweets updateSweet(Integer id, SweetRequestDTO sweet) throws NoSuchObjectException {
        Sweets s = buscarPorId(id);

        s.setNombre(sweet.getNombre());
        s.setPeso(sweet.getPeso());
        s.setPrecio(sweet.getPrecio());
        sweetRepo.save(s);

        LOGGER.info("Sweet updated: " + s.getNombre() + "/Price" + s.getPrecio() + "/Measure" + s.getPeso());

        return s;


    }

    public List<Sweets> listarSweets() {

        List<Sweets> sw = sweetRepo.findAll();
        return sw;
    }

    public List<Sweets> allAvailableSweets() {
        List<Sweets> sweets = sweetRepo.findAll();
        sweets.removeIf(s -> !s.isState());
        return sweets;
    }

    public List<Sweets> allUnavailableSweets() {
        List<Sweets> sweets = sweetRepo.findAll();
        sweets.removeIf(s -> s.isState());
        return sweets;
    }


    @Transactional
    public void eliminarSweet(Integer id) throws NoSuchObjectException {
        Sweets sweet = buscarPorId(id);
        Integer sweetId = id;
        List<Purchase> purchases = pedidoRepo.purchaseBySweets(id);
        List<Integer> purchasesId = new ArrayList<>();



        for (Purchase p : purchases) {
            purchasesId.add(p.getId());
            p.getSweets().removeIf(s -> Objects.equals(s.getId(), id));
        }

        for (Integer i : purchasesId) {
            LOGGER.info("Order id deleted: " + i);
        }

        sweetRepo.delete(sweet);
        LOGGER.info("Sweet id deleted: " + id);
    }


    @Transactional
    public void setSweetState(Integer id) throws NoSuchObjectException {
        Sweets sweet = buscarPorId(id);

        sweet.setState(!sweet.isState());

        LOGGER.info("Status update: " + sweet.getNombre() + "/" + sweet.isState());

    }

    public Sweets buscarPorId(Integer id) throws NoSuchObjectException {


        Optional<Sweets> optional = sweetRepo.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchObjectException("El sweet solicitado no se encuentra registrado");
        }


    }

    public List<Sweets> listaDeSweetsPorId(List<Integer> id_sweets) throws NoSuchObjectException {

        List<Sweets> sweets = new ArrayList<>();

        for (Integer i : id_sweets) {
            sweets.add(buscarPorId(i));
        }

        return sweets;


    }


    public void validacionData(SweetRequestDTO dto) throws ValidacionException {

        if (dto.getNombre().isBlank()) {
            throw new ValidacionException("El nombre del sweet no debe ser nulo ni debe estar vacio");
        }

        if (dto.getPrecio() == null) {
            throw new ValidacionException("El precio del sweet no debe ser nulo ni debe estar vacio");
        }
        if (dto.getPeso() == null) {
            throw new ValidacionException("El peso del sweet no debe ser nula ni debe estar vacia");
        }

    }

    public Sweets conversorDTO(SweetRequestDTO dto) {
        Sweets sweet = new Sweets();

        sweet.setNombre(dto.getNombre());
        sweet.setPrecio(dto.getPrecio());
        sweet.setPeso(dto.getPeso());

        return sweet;

    }

    public String uploadImageToFile(MultipartFile img) {

        Path directorioRecursos = Paths.get("src//main//resources//static//img//sweets");
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
        return "/img/sweets/" + img.getOriginalFilename();
    }


}
