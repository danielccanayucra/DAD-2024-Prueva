package jdcy.com.msproducto.service.impl;

import jdcy.com.msproducto.dto.CategoryDto;
import jdcy.com.msproducto.dto.ProductoDto;
import jdcy.com.msproducto.entity.Producto;
import jdcy.com.msproducto.exeption.ResourceNotFoundException;
import jdcy.com.msproducto.feign.CategoryFeign;
import jdcy.com.msproducto.repository.ProductoRepository;
import jdcy.com.msproducto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoryFeign categoryFeign;

    @Override
    public List<Producto> list() {
        List<Producto> products = productoRepository.findAll();

        for (Producto producto : products) {
            // Obtener los detalles del cliente
            ResponseEntity<CategoryDto> categoryResponse = categoryFeign.getById(producto.getCategoryId());
            if (categoryResponse.getStatusCode().is2xxSuccessful() && categoryResponse.getBody() != null) {
                producto.setCategoryDto(categoryResponse.getBody());
            }
        }

        return products;
    }
    @Override
    public Optional<ProductoDto> findById(Integer id) {
        return productoRepository.findById(id).map(product -> {
            ProductoDto productDto = mapToDto(product);

            // Llamada a Feign para obtener detalles de la categoría
            if (product.getCategoryId() != null) {
                ResponseEntity<CategoryDto> categoryResponse = categoryFeign.getById(product.getCategoryId());
                if (categoryResponse.getStatusCode().is2xxSuccessful() && categoryResponse.getBody() != null) {
                    productDto.setCategoryDto(categoryResponse.getBody());
                }
            }

            return productDto;
        });
    }

    public Producto save(Producto producto) {
        // Verificar si el categoria existe
        ResponseEntity<CategoryDto> categoryResponse = categoryFeign.getById(producto.getCategoryId());
        if (!categoryResponse.getStatusCode().is2xxSuccessful() || categoryResponse.getBody() == null) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + producto.getCategoryId());
        }

        CategoryDto categoryDto = categoryResponse.getBody();
        producto.setCategoryDto(categoryDto);


        // Guardar el producto en la base de datos
        Producto savedProduct = productoRepository.save(producto);


        return savedProduct;
    }

    @Override
    public ProductoDto update(Integer id, ProductoDto productDto) {
        Producto existingProduct = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        existingProduct.setName(productDto.getName());
        existingProduct.setPrecio(productDto.getPrecio());
        existingProduct.setStock(productDto.getStock());
        existingProduct.setCategoryId(productDto.getCategoryId());

        Producto updatedProduct = productoRepository.save(existingProduct);
        return mapToDto(updatedProduct);
    }

    @Override
    public void delete(Integer id) {
        Producto product = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        productoRepository.delete(product);
    }

    // Métodos auxiliares para mapear entre DTO y entidad
    private ProductoDto mapToDto(Producto producto) {
        ProductoDto productDto = new ProductoDto();
        productDto.setId(producto.getId());
        productDto.setName(producto.getName());
        productDto.setPrecio(producto.getPrecio());
        productDto.setStock(producto.getStock());
        productDto.setCategoryId(producto.getCategoryId());
        return productDto;
    }

    private Producto mapToEntity(ProductoDto productDto) {
        Producto product = new Producto();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrecio(productDto.getPrecio());
        product.setStock(productDto.getStock());
        product.setCategoryId(productDto.getCategoryId());
        return product;
    }

}
