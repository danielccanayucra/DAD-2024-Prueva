package jdcy.com.msproducto.service;

import jdcy.com.msproducto.dto.ProductoDto;
import jdcy.com.msproducto.entity.Producto;


import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> list();

    Optional<ProductoDto> findById(Integer id);

    Producto save(Producto producto);

    ProductoDto update(Integer id, ProductoDto productoDto);

    void delete(Integer id);
}
