package vendingmachine;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.Item;
import vendingmachine.utils.Util;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VliadationTest {

    @Test
    void 사용자_입력값으로부터_item을_추출한다() {
        String input = "[콜라,1500,20];[사이다,1000,10]";
        assertThat(Util.getItems(input)).
                contains(
                        new Item("콜라", 1500, 20),
                        new Item("사이다", 1000, 10));
    }

}
