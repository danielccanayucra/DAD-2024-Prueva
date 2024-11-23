package upeu.edu.pe.msorder.entity;

import jakarta.persistence.*;
import lombok.Data;
import upeu.edu.pe.msorder.dto.ProductDto;

@Entity
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private Double amount;
    private Integer productId;
    @Transient
    private ProductDto productDto;

    public OrderDetail() {
        this.price = (double) 0;
        this.amount = (double) 0;
    }
}
