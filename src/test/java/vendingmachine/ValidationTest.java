package vendingmachine;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.utils.InputValidation;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ValidationTest {

    @Nested
    class 사용자_입력값으로부터_item을_추출한다 {
        @Test
        void 정상적인_item_추출() {
            String input = "[콜라,1500,20];[사이다,1000,10]";
            assertThatCode(() -> InputValidation.validateItem(input))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @ValueSource(strings = {"[콜라,1500,20;[사이다,1000,10]", "[콜라,1500,20][사이다,1000,10]", "[콜라,1500,20];[사이다,1000,10"})
        void 비정상적인_포맷의_입력은_예외를_발생시킨다(String value) {
            assertThatThrownBy((() -> InputValidation.validateItem(value)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }


}
