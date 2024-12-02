package jdcy.com.msproducto.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProductoRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String code;
    @NotNull
    private Double  precio;
    @NotNull
    private Integer stock;

    @NotNull
    private Integer categoryId;
}
