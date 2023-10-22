package kitchenpos.dao;

import kitchenpos.domain.order.Order;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Repository<Order, Long> {
    Order save(Order entity);

    Optional<Order> findById(Long id);

    List<Order> findAll();
}
