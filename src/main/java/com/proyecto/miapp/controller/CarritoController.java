package com.proyecto.miapp.controller;

import com.proyecto.miapp.entity.Carrito;
import com.proyecto.miapp.service.CarritoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping
    public Carrito crearCarrito() {
        return carritoService.crearCarrito();
    }

    @PostMapping("/{carritoId}/agregar")
    public Carrito agregarProducto(@PathVariable Long carritoId,
                                   @RequestParam Long productoId,
                                   @RequestParam Integer cantidad) {

        return carritoService.agregarProducto(
                carritoId,
                productoId,
                cantidad
        );
    }

    @GetMapping("/{carritoId}/total")
    public Double total(@PathVariable Long carritoId) {
        return carritoService.calcularTotal(carritoId);
    }

    @GetMapping("/listar")
    public List<Carrito> listar() {
        return carritoService.listar();
    }
}