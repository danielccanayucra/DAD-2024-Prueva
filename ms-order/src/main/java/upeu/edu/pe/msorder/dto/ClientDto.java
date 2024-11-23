package upeu.edu.pe.msorder.dto;

import lombok.Data;

@Data
public class ClientDto {
    private Integer id;
    private String name;
    private String documentNumber;
    private Boolean state;
}
