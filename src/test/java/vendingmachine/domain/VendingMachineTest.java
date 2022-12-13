package vendingmachine.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.items.Item;
import vendingmachine.dto.output.PrintChangeDto;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VendingMachineTest {

    @Nested
    class 모든_잔돈을_반환하는_시나리오 {

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
            assertTrue(vendingMachine.canPurchase(new Item("콜라")));
            assertTrue(vendingMachine.canPurchase(new Item("사이다")));
        }

        @Test
        void 모든_잔돈을_반환() {
            vendingMachine.purchase(new Item("콜라"), true);
            assertEquals(vendingMachine.printInputMoney().getInputMoney(), new Money(1500));

            vendingMachine.purchase(new Item("사이다"), true);
            assertEquals(vendingMachine.printInputMoney().getInputMoney(), new Money(500));

            assertFalse(vendingMachine.haveEnoughMoney());

            TreeMap<Coin, Integer> changeMap = vendingMachine.getChangeMap().getChangeMap();
            assertEquals(changeMap.get(Coin.COIN_100), 3);
            assertEquals(changeMap.get(Coin.COIN_50), 2);
            assertEquals(changeMap.get(Coin.COIN_10), 5);
        }

        @Test
        void 반환할_잔돈이_없음() {
            vendingMachine.purchase(new Item("콜라"), true);
            assertEquals(vendingMachine.printInputMoney().getInputMoney(), new Money(1500));

            vendingMachine.purchase(new Item("콜라"), true);
            assertEquals(vendingMachine.printInputMoney().getInputMoney(), new Money(0));

            assertFalse(vendingMachine.haveEnoughMoney());

            TreeMap<Coin, Integer> changeMap = vendingMachine.getChangeMap().getChangeMap();
            assertEquals(changeMap.size(), 0);
        }
    }

    @Nested
    class 일부_잔돈만을_반환하는_시나리오 {

        private VendingMachine vendingMachine;

        @BeforeEach
        void 잔돈_초기화() {
            vendingMachine = new VendingMachine(700, amount -> {
                TreeMap<Coin, Integer> result = new TreeMap<>();
                result.put(Coin.COIN_500, 1);
                result.put(Coin.COIN_100, 1);
                result.put(Coin.COIN_50, 1);
                result.put(Coin.COIN_10, 5);
                return result;
            });
            vendingMachine.setItems("[콜라,1100,20];[사이다,1300,10]");
            vendingMachine.setMoney(3000);
        }

        @Test
        void 잔돈을_반환() {
            vendingMachine.purchase(new Item("콜라"), true);
            assertEquals(vendingMachine.printInputMoney().getInputMoney(), new Money(1900));

            vendingMachine.purchase(new Item("사이다"), true);
            assertEquals(vendingMachine.printInputMoney().getInputMoney(), new Money(600));

            assertFalse(vendingMachine.haveEnoughMoney());

            PrintChangeDto changeMap1 = vendingMachine.getChangeMap();
            TreeMap<Coin, Integer> changeMap = changeMap1.getChangeMap();
            assertEquals(changeMap.get(Coin.COIN_500), 1);
            assertEquals(changeMap.get(Coin.COIN_100), 1);
            assertEquals(changeMap.size(), 2);
        }
    }
}