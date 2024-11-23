package upeu.edu.pe.msorder.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean state;
}
