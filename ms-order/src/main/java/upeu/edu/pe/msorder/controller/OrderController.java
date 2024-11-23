package upeu.edu.pe.msorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.msorder.dto.ClientDto;
import upeu.edu.pe.msorder.dto.ErrorResponseDto;
import upeu.edu.pe.msorder.dto.ProductDto;
import upeu.edu.pe.msorder.entity.Order;
import upeu.edu.pe.msorder.entity.OrderDetail;
import upeu.edu.pe.msorder.feign.CatalogFeign;
import upeu.edu.pe.msorder.feign.ClientFeign;
import upeu.edu.pe.msorder.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientFeign clientFeign;
    @Autowired
    private CatalogFeign catalogFeign;

    @GetMapping
    public ResponseEntity<List<Order>> list() {
        return ResponseEntity.ok(orderService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getById(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Order order) {

        ClientDto clientDto = clientFeign.getById(order.getCLienteId()).getBody();
        if (clientDto == null || clientDto.getId() == null) {
            String errorMessage = "Error: Cliente no encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }
        for (OrderDetail orderDetail : order.getOrderDetails()){
            ProductDto productDto = catalogFeign.getById(orderDetail.getProductId()).getBody();
            if (productDto==null || productDto.getId()==null){
                String errorMessage = "Error: producto no encontrado.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }

        Order newOrder = orderService.save(order);
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable(required = true) Integer id, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.update(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Order>> delete(@PathVariable(required = true) Integer id) {
        orderService.delete(id);
        return ResponseEntity.ok(orderService.list());
    }
}
