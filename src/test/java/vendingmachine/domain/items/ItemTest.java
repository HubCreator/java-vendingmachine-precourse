package vendingmachine.domain.items;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 금액과 수량은 숫자로 입력하셔야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "9", "111", "123"})
        void _10원_단위가_아닌_입력(String value) {
            assertThatThrownBy(() -> new Item("콜라", value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 10 단위로 입력하셔야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"10", "90", "0"})
        void _100원_이하의_입력(String value) {
            assertThatThrownBy(() -> new Item("콜라", value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 상품의 가격으로는 100원 이상을 입력해야 합니다.");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "110", "300"})
    void 올바른_상품가격_입력(String value) {
        assertThatCode(() -> new Item("콜라", value))
                .doesNotThrowAnyException();
    }
}