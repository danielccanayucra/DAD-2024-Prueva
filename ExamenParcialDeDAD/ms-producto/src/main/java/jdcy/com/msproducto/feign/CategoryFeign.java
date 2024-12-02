package jdcy.com.msproducto.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jdcy.com.msproducto.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-category-service", path = "/category")
public interface CategoryFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "categoryListByIdCB", fallbackMethod = "categoryListById")

    public ResponseEntity<CategoryDto> getById(@PathVariable Integer id);

    default ResponseEntity<CategoryDto> categoryListById(Integer id, Exception e) {
        return ResponseEntity.ok(new CategoryDto());
    }
}
