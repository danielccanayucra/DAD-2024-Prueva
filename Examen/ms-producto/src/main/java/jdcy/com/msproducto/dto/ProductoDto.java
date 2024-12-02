package jdcy.com.msproducto.dto;

import lombok.Data;

@Data
public class ProductoDto {
    private Integer id;
    private String name;
    private String description;
    private String code;
    private Double  precio;
    private Integer stock;
    private Integer categoryId;
    private CategoryDto categoryDto;
}
