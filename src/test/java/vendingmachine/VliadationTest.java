package vendingmachine;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VliadationTest {

    @Test
    void case1() {
        String input = "[콜라,1500,20];[사이다,1000,10]";
        assertThat(ValidationUtil.getItems(input)).
                contains(
                        new Item("콜라", 1500, 20),
                        new Item("사이다", 1000, 10));
    }
}
