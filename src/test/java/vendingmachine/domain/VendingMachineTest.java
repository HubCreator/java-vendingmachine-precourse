package vendingmachine.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.items.Item;

import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    void 잔돈_초기화() {
        vendingMachine = new VendingMachine(450, amount -> {
            TreeMap<Coin, Integer> result = new TreeMap<>();
            result.put(Coin.COIN_100, 3);
            result.put(Coin.COIN_50, 2);
            result.put(Coin.COIN_10, 5);
            return result;
        });
        vendingMachine.setItems("[콜라,1500,20];[사이다,1000,10]");
        vendingMachine.setMoney(3000);
    }

    @Test
    void 구매_가능한_상품() {
        assertThat(vendingMachine.canPurchase(new Item("콜라"))).isTrue();
        assertThat(vendingMachine.canPurchase(new Item("사이다"))).isTrue();
    }

    @Test
    void 상품_구매() {
        vendingMachine.purchase(new Item("콜라"), true);
        assertThat(vendingMachine.printInputMoney().getInputMoney()).isEqualTo(new Money(1500));

        vendingMachine.purchase(new Item("사이다"), true);
        assertThat(vendingMachine.printInputMoney().getInputMoney()).isEqualTo(new Money(500));
    }


}