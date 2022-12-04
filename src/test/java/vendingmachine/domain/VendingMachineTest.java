package vendingmachine.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VendingMachineTest {

    Items items;
    VendingMachine vendingMachine;

    @BeforeEach
    void init() {
        items = new Items(
                Arrays.asList(
                        new Item("콜라", 1500, 3),
                        new Item("사이다", 1000, 20)));
        vendingMachine = VendingMachine.create(null,items, 3000);
    }


    @Test
    void 상품을_구매할_때마다_잔액을_확인한다() {
        assertThat(vendingMachine.canPurchase(new Item("콜라"))).isTrue();
        vendingMachine.purchase(new Item("콜라"));
        assertThat(vendingMachine.getPurchase()).isEqualTo(1500);

        assertThat(vendingMachine.canPurchase(new Item("사이다"))).isTrue();
        vendingMachine.purchase(new Item("사이다"));
        assertThat(vendingMachine.getPurchase()).isEqualTo(500);
    }
}