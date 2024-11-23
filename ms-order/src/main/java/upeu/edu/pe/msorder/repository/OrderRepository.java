package upeu.edu.pe.msorder.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import upeu.edu.pe.msorder.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
