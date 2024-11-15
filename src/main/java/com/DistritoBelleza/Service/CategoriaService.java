package com.DistritoBelleza.Service;

import com.DistritoBelleza.Entity.Categoria;
import com.DistritoBelleza.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorias() {
        return categoriaRepository.getCategorias();
    }

    public void insertCategoria(String descripcion, String rutaImagen, Boolean activo) {
        categoriaRepository.insertCategoria(descripcion, rutaImagen, activo);
    }

    public void updateCategoria(Long idCategoria, String descripcion, String rutaImagen, Boolean activo) {
        categoriaRepository.updateCategoria(idCategoria, descripcion, rutaImagen, activo);
    }

    public void deleteCategoria(Long idCategoria) {
        categoriaRepository.deleteCategoria(idCategoria);
    }
}
