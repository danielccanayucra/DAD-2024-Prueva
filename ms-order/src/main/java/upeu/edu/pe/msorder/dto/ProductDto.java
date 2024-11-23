package upeu.edu.pe.msorder.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean state;
    private CategoryDto category;
}
