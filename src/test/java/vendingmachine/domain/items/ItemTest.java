package vendingmachine.domain.items;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    void name() {
        Item 콜라1 = new Item("콜라");
        Item 콜라2 = new Item("콜라");
        assertThat(콜라1).isEqualTo(콜라2);
    }
}