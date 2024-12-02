package jdcy.com.msproducto.entity;

import jakarta.persistence.*;
import jdcy.com.msproducto.dto.CategoryDto;
import lombok.Data;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String code;
    private Double  precio;
    private Integer stock;
    private Integer categoryId;

    @Transient
    private CategoryDto categoryDto;
}
