package vendingmachine;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.enums.ErrorMessage;
import vendingmachine.utils.InputValidation;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ValidationTest {

    @Nested
    class 사용자가_입력한_금액이_올바른지_판단한다 {

        @ParameterizedTest
        @ValueSource(strings = {"450", "1000", "100"})
        void 정상적인_투입_금액(String input) {
            assertThatCode(() -> InputValidation.validateAmount(input))
                    .doesNotThrowAnyException();
        }

        @Nested
        class 비정상적인_투입_금액 {

            @ParameterizedTest
            @ValueSource(strings = {"101", "99"})
            void 유효_범위가_아닌_입력(String input) {
                assertThatThrownBy(() -> InputValidation.validateAmount(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
            }

            @ParameterizedTest
            @ValueSource(strings = {"", " ", "hello"})
            void 공백_혹은_문자열_입력(String input) {
                assertThatThrownBy(() -> InputValidation.validateAmount(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ErrorMessage.IS_NOT_DIGIT.getMessage());
            }
        }
    }


    @Nested
    class 사용자_입력값으로부터_item을_추출한다 {

        @Test
        void 정상적인_item_추출() {
            String input = "[콜라,1500,20];[사이다,1000,10]";
            assertThatCode(() -> InputValidation.validateItem(input))
                    .doesNotThrowAnyException();
        }

        @Nested
        class 비정상적인_item_입력 {

            @ParameterizedTest
            @ValueSource(strings = {
                    "[콜라,1500,20;[사이다,1000,10]",
                    "[콜라,1500,20][사이다,1000,10]",
                    "[콜라,1500,20];[사이다,1000,10"})
            void 비정상적인_포맷의_입력은_예외를_발생시킨다(String value) {
                assertThatThrownBy(() -> InputValidation.validateItem(value)).
                        isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ErrorMessage.INVALID_FORMAT.getMessage());
            }

            @ParameterizedTest
            @ValueSource(strings = {
                    "[콜라,99,20];[사이다,1000,10]",
                    "[콜라,101,20];[사이다,1000,10]"})
            void 비정상적인_상품_가격은_예외를_발생시킨다(String value) {
                assertThatThrownBy(() -> InputValidation.validateItem(value))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ErrorMessage.INVALID_RANGE_INPUT_PRICE.getMessage());
            }
        }
    }
}
