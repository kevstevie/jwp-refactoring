package kitchenpos.application;

import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Product;
import kitchenpos.fake.FakeProductDao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ProductServiceTest {

    private ProductDao productDao = new FakeProductDao();
    private ProductService productService = new ProductService(productDao);

    @Test
    void 상품을_생성한다() {
        Product product = new Product(null, "후라이드", BigDecimal.valueOf(16000));
        Product saved = productService.create(product);

        assertThat(product).usingRecursiveComparison().isEqualTo(saved);
    }

    @Test
    void 상품_가격은_null일_수_없다() {
        Product product = new Product(null, "후라이드", null);

        assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상품_가격은_음수일_수_없다() {
        Product product = new Product(null, "후라이드", BigDecimal.valueOf(-16000));

        assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상품_전체를_조회한다() {
        productService.create(new Product(null, "후라이드", BigDecimal.valueOf(16000)));
        productService.create(new Product(null, "후라이드", BigDecimal.valueOf(16000)));
        productService.create(new Product(null, "후라이드", BigDecimal.valueOf(16000)));

        assertThat(productService.list()).hasSize(3);
    }

}