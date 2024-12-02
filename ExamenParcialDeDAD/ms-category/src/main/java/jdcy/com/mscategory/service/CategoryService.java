package jdcy.com.mscategory.service;

import jdcy.com.mscategory.entity.Category;

import java.util.List;
import java.util.Optional;

public interface  CategoryService {
    public List<Category> listar();
    public Category guardar(Category categoria);
    public Category actualizar(Category categoria);
    public Optional<Category> listarPorId(Integer id);
    public void eliminarPorId(Integer id);
}
