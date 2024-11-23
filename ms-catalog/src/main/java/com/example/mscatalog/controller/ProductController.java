
package com.example.mscatalog.controller;

import com.example.mscatalog.entity.Product;
import com.example.mscatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(productService.getById(id).get());
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(required = true) Integer id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Product>> delete(@PathVariable(required = true) Integer id) {
        productService.delete(id);
        return ResponseEntity.ok(productService.list());
    }
}
