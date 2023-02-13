package com.lumbersoft.alexandria.controladores;

import com.lumbersoft.alexandria.entidades.Cafe;
import com.lumbersoft.alexandria.entidades.Libro;
import com.lumbersoft.alexandria.entidades.Mesa;
import com.lumbersoft.alexandria.entidades.Pedido;
import com.lumbersoft.alexandria.entidades.UbicacionMesa;
import com.lumbersoft.alexandria.excepciones.AlfaException;
import com.lumbersoft.alexandria.servicios.CafeService;
import com.lumbersoft.alexandria.servicios.ClienteService;
import com.lumbersoft.alexandria.servicios.LibroService;
import com.lumbersoft.alexandria.servicios.MesaService;
import com.lumbersoft.alexandria.servicios.PedidoService;
import com.lumbersoft.alexandria.servicios.UbicacionMesaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pedido")
public class PedidoControlador {

    @Autowired
    private MesaService ms;

    @Autowired
    private CafeService cs;

    @Autowired
    private ClienteService cls;

    @Autowired
    private PedidoService ps;

    @Autowired
    private LibroService ls;

    @Autowired
    private UbicacionMesaService ums;

    @GetMapping("/pedidoinit")
    public String crearPedido(ModelMap modelo) {

        List<Mesa> mesas = ms.listarMesas();
        List<Cafe> cafes = cs.listarCafes();
        List<Libro> libros = ls.listarLibros();
        List<Pedido> pedidos = ps.listarPedidos();

        modelo.addAttribute("mesas", mesas);
        modelo.addAttribute("cafes", cafes);
        modelo.addAttribute("libros", libros);
        modelo.put("pedidos", pedidos);

        return "pedido.html";

    }

    @PostMapping("/crearPedido")
    public String crearPedido(@RequestParam(required = false) Integer idCafe, @RequestParam(required = false) Integer idMesa,
            @RequestParam(required = false) String nombreCliente, @RequestParam(required = false) String apellidoCliente, @RequestParam(required = false) Long isbn, ModelMap modelo) {

        try {
            ps.generarPedido(idCafe, isbn, idMesa, nombreCliente, apellidoCliente);
            List<Mesa> mesas = ms.listarMesas();
            List<Cafe> cafes = cs.listarCafes();
            List<Libro> libros = ls.listarLibros();
            List<Pedido> pedidos = ps.listarPedidos();

            modelo.addAttribute("mesas", mesas);
            modelo.addAttribute("cafes", cafes);
            modelo.addAttribute("libros", libros);
            modelo.put("pedidos", pedidos);
        } catch (Exception e) {
            List<Mesa> mesas = ms.listarMesas();
            List<Cafe> cafes = cs.listarCafes();
            List<Libro> libros = ls.listarLibros();
            List<Pedido> pedidos = ps.listarPedidos();

            modelo.addAttribute("mesas", mesas);
            modelo.addAttribute("cafes", cafes);
            modelo.addAttribute("libros", libros);
            modelo.put("pedidos", pedidos);
            modelo.put("msgError", e.getMessage());
        }

        return "pedido.html";

    }

    @GetMapping("/eliminarPedido/{id}")//Por alguna razon este controlado si bien modifica en la BD, la anotacion PostMapping larga un error 405
    public String eliminarPedido(@PathVariable Integer id, ModelMap modelo) throws AlfaException {

        ps.eliminarPedido(id);
        List<Pedido> pedidos = ps.listarPedidos();
        modelo.put("pedidos", pedidos);

        return "pedido.html";
    }

    @GetMapping("/modificarPedido/{id}")
    public String modificarPedido(@PathVariable Integer id, ModelMap modelo) throws AlfaException {

        modelo.put("pedido", ps.buscarPorId(id));
        List<Pedido> pedidos = ps.listarPedidos();
        List<Mesa> mesas = ms.listarMesas();
        List<Cafe> cafes = cs.listarCafes();
        List<Libro> libros = ls.listarLibros();

        modelo.addAttribute("mesas", mesas);
        modelo.addAttribute("cafes", cafes);
        modelo.addAttribute("libros", libros);
        modelo.put("pedidos", pedidos);

        return "pedidoModif.html";
    }

    @PostMapping("/modificarPedido/{id}")
    public String modificarPedido(@PathVariable Integer id, @RequestParam Integer idCafe, @RequestParam Integer idMesa,
            @RequestParam String nombreCliente, @RequestParam String apellidoCliente, @RequestParam Long isbn, ModelMap modelo) throws AlfaException {

        ps.modificarPedido(id, nombreCliente, apellidoCliente, isbn, idCafe, idMesa);

        List<Pedido> pedidos = ps.listarPedidos();
        List<Mesa> mesas = ms.listarMesas();
        List<Cafe> cafes = cs.listarCafes();
        List<Libro> libros = ls.listarLibros();

        modelo.addAttribute("mesas", mesas);
        modelo.addAttribute("cafes", cafes);
        modelo.addAttribute("libros", libros);
        modelo.put("pedidos", pedidos);

        return "pedido.html";

    }

    @GetMapping("/avanzarEstadoPedido/{id}")
    public String avanzarEstadoPedido(@PathVariable Integer id, ModelMap modelo) throws AlfaException {

        try {

            ps.cambiarEstadoPedido(id);
            List<Pedido> pedidos = ps.listarPedidos();
            modelo.put("pedidos", pedidos);
            return "pedido.html";

        } catch (AlfaException e) {

            List<Pedido> pedidos = ps.listarPedidos();
            modelo.put("pedidos", pedidos);
            modelo.put("msgError", e.getMessage());
        }

        return "pedido.html";
    }

}
