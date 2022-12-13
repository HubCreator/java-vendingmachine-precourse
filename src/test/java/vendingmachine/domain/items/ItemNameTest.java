package vendingmachine.domain.items;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemNameTest {

    @Test
    void name() {
        ItemName 콜라1 = new ItemName("콜라");
        ItemName 콜라2 = new ItemName("콜라");
        assertThat(콜라1).isEqualTo(콜라2);
    }
}