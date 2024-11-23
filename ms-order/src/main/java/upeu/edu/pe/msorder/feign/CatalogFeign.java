package upeu.edu.pe.msorder.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upeu.edu.pe.msorder.dto.ProductDto;

@FeignClient(name = "ms-catalog-service", path = "/product")

public interface CatalogFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "orderByIdCB", fallbackMethod = "fallbackProductById")
    public ResponseEntity<ProductDto> getById(@PathVariable(required = true) Integer id);

    default ResponseEntity<ProductDto> fallbackProductById(Integer id, Exception e) {
        ProductDto productDto = new ProductDto();
        productDto.setName("Servicio no disponible de producto");
        return ResponseEntity.ok(productDto);
    }

}
