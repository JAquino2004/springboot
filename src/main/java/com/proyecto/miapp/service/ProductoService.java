/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.miapp.service;

import com.proyecto.miapp.entity.Producto;
import com.proyecto.miapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Listar productos
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // Guardar producto
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    // Buscar producto por ID
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
}