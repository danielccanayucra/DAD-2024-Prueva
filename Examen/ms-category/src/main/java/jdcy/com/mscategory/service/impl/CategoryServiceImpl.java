package jdcy.com.mscategory.service.impl;

import jdcy.com.mscategory.entity.Category;
import jdcy.com.mscategory.repository.CategoryRepository;
import jdcy.com.mscategory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> listar() {
        return categoryRepository.findAll();
    }

    @Override
    public Category guardar(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category actualizar(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> listarPorId(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        categoryRepository.deleteById(id);
    }
}
