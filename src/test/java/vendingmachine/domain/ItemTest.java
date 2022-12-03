package vendingmachine.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.enums.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ItemTest {

    @ParameterizedTest
    @ValueSource(ints = {99, 101, 999})
    void 올바르지_않은_price값은_예외를_발생시킨다(int value) {
        assertThatThrownBy(() -> new Item("name", value, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
    }
}