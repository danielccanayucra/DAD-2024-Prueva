package jdcy.com.msproducto.controller;

import jakarta.validation.Valid;
import jdcy.com.msproducto.dto.ProductoDto;
import jdcy.com.msproducto.dto.ProductoRequestDto;
import jdcy.com.msproducto.dto.ProductoResponseDto;
import jdcy.com.msproducto.entity.Producto;
import jdcy.com.msproducto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> list() {
        return ResponseEntity.ok(productoService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getProductById(@PathVariable Integer id) {
        Optional<ProductoDto> product = productoService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDto> create(@RequestBody @Valid ProductoRequestDto productoRequestDto) {
        Producto producto = new Producto();
        producto.setName(productoRequestDto.getName());
        producto.setDescription(productoRequestDto.getDescription());
        producto.setCode(productoRequestDto.getCode());
        producto.setPrecio(productoRequestDto.getPrecio());
        producto.setStock(productoRequestDto.getStock());
        producto.setCategoryId(productoRequestDto.getCategoryId());

        Producto savedProducto = productoService.save(producto);

        // Mapear la entidad guardada al DTO de respuesta
        ProductoResponseDto responseDTO = new ProductoResponseDto();
        responseDTO.setId(savedProducto.getId());
        responseDTO.setName(savedProducto.getName());
        responseDTO.setDescription(savedProducto.getDescription());
        responseDTO.setCode(savedProducto.getCode());
        responseDTO.setPrecio(savedProducto.getPrecio());
        responseDTO.setStock(savedProducto.getStock());
        responseDTO.setCategoryId(savedProducto.getCategoryId());

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> update(@PathVariable Integer id, @RequestBody ProductoDto productDto) {
        ProductoDto updatedProduct = productoService.update(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
