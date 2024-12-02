package jdcy.com.msproducto.repository;

import jdcy.com.msproducto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
