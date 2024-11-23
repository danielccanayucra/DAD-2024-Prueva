package upeu.edu.pe.msorder.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.msorder.dto.ClientDto;
import upeu.edu.pe.msorder.dto.ProductDto;

@FeignClient(name = "ms-client-service", path = "/client")

public interface ClientFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clientByIdCB", fallbackMethod = "fallbackClientById")
    public ResponseEntity<ClientDto> getById(@PathVariable(required = true) Integer id);

    default ResponseEntity<ClientDto> fallbackClientById(Integer id, Exception e) {
        ClientDto clientDto = new ClientDto();
        clientDto.setName("Servicio no disponible de cliente");
        return ResponseEntity.ok(clientDto);
    }

}
