package upeu.edu.pe.msorder.service;

import upeu.edu.pe.msorder.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> list();

    public Optional<Order> getById(Integer id);

    public Order save(Order order);

    public Order update(Integer id, Order order);

    public void delete(Integer id);

}
