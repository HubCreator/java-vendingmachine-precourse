package vendingmachine.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MoneyTest {

    @Nested
    class 올바르지_않은_입력 {

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "!", "안녕"})
        void 문자열_입력(String value) {
            assertThatThrownBy(() -> new Money(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 금액은 숫자로 입력하셔야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "13", "111"})
        void _10단위의_금액이_아닌_입력(String value) {
            assertThatThrownBy(() -> new Money(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 10 단위로 입력하셔야 합니다.");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "100", "200", "110"})
    void 정상입력(String value) {
        assertThatCode(() -> new Money(value))
                .doesNotThrowAnyException();
    }
}