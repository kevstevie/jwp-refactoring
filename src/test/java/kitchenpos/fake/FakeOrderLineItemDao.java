package kitchenpos.fake;

import kitchenpos.dao.OrderLineItemDao;
import kitchenpos.domain.OrderLineItem;

import java.util.*;
import java.util.stream.Collectors;

public class FakeOrderLineItemDao implements OrderLineItemDao {

    private static Map<Long, OrderLineItem> orderLineItems = new HashMap<>();
    private static Long id = 0L;

    @Override
    public OrderLineItem save(OrderLineItem entity) {
        if (entity.getSeq() != null) {
            orderLineItems.put(entity.getSeq(), entity);
            return entity;
        }
        entity.setSeq(++id);
        orderLineItems.put(id, entity);
        return entity;
    }

    @Override
    public Optional<OrderLineItem> findById(Long id) {
        return Optional.ofNullable(orderLineItems.get(id));
    }

    @Override
    public List<OrderLineItem> findAll() {
        return new ArrayList<>(orderLineItems.values());
    }

    @Override
    public List<OrderLineItem> findAllByOrderId(Long orderId) {
        return orderLineItems.values().stream()
                .filter(orderLineItem -> orderLineItem.getOrderId().equals(orderId))
                .collect(Collectors.toList());
    }
}
