package upeu.edu.pe.msorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.msorder.entity.Order;
import upeu.edu.pe.msorder.entity.OrderDetail;
import upeu.edu.pe.msorder.feign.CatalogFeign;
import upeu.edu.pe.msorder.repository.OrderRepository;
import upeu.edu.pe.msorder.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderSeriviceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CatalogFeign catalogFeign;

    @Override
    public List<Order> list() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        for (OrderDetail orderDetail : order.get().getOrderDetails()) {
            System.out.println(catalogFeign.getById(orderDetail.getProductId()).getBody().toString());
            orderDetail.setProductDto(catalogFeign.getById(orderDetail.getProductId()).getBody());
        }

        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Integer id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
