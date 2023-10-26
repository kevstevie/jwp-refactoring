package kitchenpos.table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "order_table")
@Entity
public class OrderTable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int numberOfGuests;

    private boolean empty;

    private boolean grouped;

    public OrderTable() {
    }

    public OrderTable(int numberOfGuests, boolean empty, boolean grouped) {
        this.id = null;
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
        this.grouped = grouped;
    }

    public void changeEmpty(boolean empty) {
        if (this.grouped) {
            throw new IllegalArgumentException("그룹된 테이블을 비울 수 없습니다.");
        }
        this.empty = empty;
    }

    public void changeNumberOfGuests(int numberOfGuests) {
        if (numberOfGuests <= 0) {
            throw new IllegalArgumentException();
        }
        if (this.empty) {
            throw new IllegalArgumentException();
        }

        this.numberOfGuests = numberOfGuests;
    }

    public boolean hasTableGroup() {
        return this.grouped;
    }

    public boolean hasNoTableGroup() {
        return !this.grouped;
    }

    public void unGroup() {
        this.grouped = false;
        this.empty = true;
    }

    public boolean isNotEmpty() {
        return !this.empty;
    }

    public void makeFull() {
        this.empty = false;
    }

    public void makeGrouped() {
        this.grouped = true;
    }

    public Long getId() {
        return id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isGrouped() {
        return grouped;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderTable)) {
            return false;
        }
        OrderTable orderTable = (OrderTable) o;
        return id.equals(orderTable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}