package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Producto;
import com.DistritoBelleza.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.getProductos();
    }

    public void insertProducto(Long idCategoria, Long idMaterial, String descripcion, String detalle, Double precio, Integer existencia, String rutaImagen, Boolean activo) {
        productoRepository.insertProducto(idCategoria, idMaterial, descripcion, detalle, precio, existencia, rutaImagen, activo);
    }

    public void updateProducto(Long idProducto, Long idCategoria, Long idMaterial, String descripcion, String detalle, Double precio, Integer existencia, String rutaImagen, Boolean activo) {
        productoRepository.updateProducto(idProducto, idCategoria, idMaterial, descripcion, detalle, precio, existencia, rutaImagen, activo);
    }

    public void deleteProducto(Long idProducto) {
        productoRepository.deleteProducto(idProducto);
    }
}