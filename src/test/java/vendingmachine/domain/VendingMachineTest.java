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
                        new Item("콜라", 1000, 3),
                        new Item("사이다", 500, 2)));
        vendingMachine = VendingMachine.create(items);
        vendingMachine.initializePurchaseAmount(3000);
    }


    @Test
    void 상품을_구매할_때마다_잔액을_확인한다() {
        vendingMachine.canPurchase("콜라");
        assertThat(vendingMachine.getPurchaseAmount()).isEqualTo(2000);
        vendingMachine.canPurchase("콜라");
        assertThat(vendingMachine.getPurchaseAmount()).isEqualTo(1000);
        vendingMachine.canPurchase("사이다");
        assertThat(vendingMachine.getPurchaseAmount()).isEqualTo(500);
        vendingMachine.canPurchase("사이다");
        assertThat(vendingMachine.getPurchaseAmount()).isEqualTo(0);
    }
}