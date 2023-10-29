package order;

import kitchenpos.order.MenuHistoryRecorder;
import kitchenpos.order.Order;
import kitchenpos.order.OrderLineItem;
import kitchenpos.order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @Mock
    private MenuHistoryRecorder menuHistoryRecorder = Mockito.mock(MenuHistoryRecorder.class);

    @Test
    void 주문_항목이_비어있으면_주문할_수_없다() {
        // given
        List<OrderLineItem> orderLineItems = List.of();
        // when & then
        assertThatThrownBy(() -> new Order(orderLineItems, 1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문_상태가_완료면_주문_상태를_변경할_수_없다() {
        // given
        List<OrderLineItem> orderLineItems = List.of(
                new OrderLineItem(1L, 1, menuHistoryRecorder)
        );
        Order order = new Order(orderLineItems, 1L);
        order.changeOrderStatus(OrderStatus.COMPLETION);
        // when & then
        assertThatThrownBy(() -> order.changeOrderStatus(OrderStatus.COMPLETION))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @EnumSource(value = OrderStatus.class, names = {"COOKING", "MEAL"})
    @ParameterizedTest
    void 주문_상태가_완료가_아니면_주문_상태를_변경할_수_있다(OrderStatus orderStatus) {
        // given
        List<OrderLineItem> orderLineItems = List.of(
                new OrderLineItem(1L, 1, menuHistoryRecorder)
        );
        Order order = new Order(orderLineItems, 1L);
        order.changeOrderStatus(orderStatus);
        // when & then
        assertThatCode(() -> order.changeOrderStatus(OrderStatus.COMPLETION))
                .doesNotThrowAnyException();
    }
}