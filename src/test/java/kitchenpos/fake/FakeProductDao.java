package kitchenpos.fake;

import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Product;

import java.util.*;

public class FakeProductDao implements ProductDao {

    private Map<Long, Product> products = new HashMap<>();
    private Long id = 0L;

    @Override
    public Product save(Product entity) {
        if (entity.getId() != null) {
            products.put(entity.getId(), entity);
            return entity;
        }
        products.put(++id, entity);
        return entity;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
