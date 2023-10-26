package kitchenpos.tablegroup;

import kitchenpos.table.OrderTable;
import kitchenpos.table.OrderTableRepository;
import kitchenpos.table.OrderTables;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TableGroupService {

    private final OrderTableRepository orderTableRepository;
    private final TableGroupRepository tableGroupRepository;
    private final UnGroupValidator unGroupValidator;

    public TableGroupService(
            final OrderTableRepository orderTableRepository,
            final TableGroupRepository tableGroupRepository,
            UnGroupValidator unGroupValidator
    ) {
        this.orderTableRepository = orderTableRepository;
        this.tableGroupRepository = tableGroupRepository;
        this.unGroupValidator = unGroupValidator;
    }

    @Transactional
    public TableGroup create(final List<Long> tableIds) {

        final List<OrderTable> savedOrderTables = orderTableRepository.findAllByIdIn(tableIds);

        if (tableIds.size() != savedOrderTables.size()) {
            throw new IllegalArgumentException();
        }

        OrderTables orderTables = new OrderTables(savedOrderTables);
        TableGroup tableGroup = orderTables.group();

        return tableGroupRepository.save(tableGroup);
    }

    @Transactional
    public TableGroup ungroup(final Long tableGroupId) {
        TableGroup tableGroup = tableGroupRepository.findById(tableGroupId)
                .orElseThrow(() -> new IllegalArgumentException("테이블 그룹이 존재하지 않습니다."));

        unGroupValidator.validate(tableGroup);

        tableGroup.unGroup();

        return tableGroup;
    }
}