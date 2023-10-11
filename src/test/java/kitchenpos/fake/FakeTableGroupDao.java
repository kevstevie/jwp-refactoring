package kitchenpos.fake;

import kitchenpos.dao.TableGroupDao;
import kitchenpos.domain.TableGroup;

import java.util.*;

public class FakeTableGroupDao implements TableGroupDao {

    private static Map<Long, TableGroup> tableGroups = new HashMap<>();
    private static Long id = 0L;

    @Override
    public TableGroup save(TableGroup entity) {
        if (entity.getId() != null) {
            tableGroups.put(entity.getId(), entity);
            return entity;
        }
        entity.setId(++id);
        tableGroups.put(id, entity);
        return entity;
    }

    @Override
    public Optional<TableGroup> findById(Long id) {
        return Optional.ofNullable(tableGroups.get(id));
    }

    @Override
    public List<TableGroup> findAll() {
        return new ArrayList<>(tableGroups.values());
    }
}