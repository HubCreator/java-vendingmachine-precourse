package vendingmachine.domain.items;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ItemTest {

    @Test
    void 기본_객체_테스트() {
        assertThat(new Item("콜라")).isEqualTo(new Item("콜라"));
    }

    @Nested
    class 올바르지_않은_상품_가격 {

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "!!"})
        void 문자열_입력(String value) {
            assertThatThrownBy(() -> new Item("콜라", value))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"10", "90", "101", "301"})
        void 올바르지_않은_입력_범위(String value) {
            assertThatThrownBy(() -> new Item("콜라", value))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "110", "300"})
    void 올바른_상품가격_입력(String value) {
        assertThatCode(() -> new Item("콜라", value))
                .doesNotThrowAnyException();
    }
}