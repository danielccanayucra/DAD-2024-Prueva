package jdcy.com.msproducto.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductoResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String code;
    private Double  precio;
    private Integer stock;
    private Integer categoryId;
    private List<CategoryDto> categoryDtos;
}
