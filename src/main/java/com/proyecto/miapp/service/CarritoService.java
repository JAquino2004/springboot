/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.miapp.service;

import com.proyecto.miapp.entity.Carrito;
import com.proyecto.miapp.entity.ItemCarrito;
import com.proyecto.miapp.entity.Producto;
import com.proyecto.miapp.repository.CarritoRepository;
import com.proyecto.miapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    public CarritoService(CarritoRepository carritoRepository,
                          ProductoRepository productoRepository) {

        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
        
    }
    
    // Crear carrito vacío
    public Carrito crearCarrito() {

        Carrito carrito = new Carrito();

        return carritoRepository.save(carrito);
    }

    // Agregar producto al carrito
    public Carrito agregarProducto(Long carritoId,
                                   Long productoId,
                                   Integer cantidad) {

        // Buscar carrito
        Carrito carrito = carritoRepository
                .findById(carritoId)
                .orElse(null);

        // Buscar producto
        Producto producto = productoRepository
                .findById(productoId)
                .orElse(null);

        // Validar existencia
        if (carrito == null || producto == null) {
            return null;
        }

        // Crear item
        ItemCarrito item = new ItemCarrito(
                producto,
                cantidad
        );

        // Agregar item al carrito
        carrito.getItems().add(item);

        // Guardar cambios
        return carritoRepository.save(carrito);
    }

    // Calcular total
    public Double calcularTotal(Long carritoId) {

        Carrito carrito = carritoRepository
                .findById(carritoId)
                .orElse(null);

        if (carrito == null) {
            return 0.0;
        }

        return carrito.getItems()
                .stream()
                .mapToDouble(item ->
                        item.getProducto().getPrecio()
                        * item.getCantidad())
                .sum();
    }
    
    public List<Carrito> listar() {
    return carritoRepository.findAll();
}
    
}
