package vendingmachine;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.Item;
import vendingmachine.validation.ValidationUtil;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VliadationTest {

    @Test
    void 사용자_입력값으로부터_item을_추출한다() {
        String input = "[콜라,1500,20];[사이다,1000,10]";
        assertThat(ValidationUtil.getItems(input)).
                contains(
                        new Item("콜라", 1500, 20),
                        new Item("사이다", 1000, 10));
    }

    @Test
    void 자판기가_보유하고_있는_금액으로부터_coin을_추출한다() {
        int sum = 0;
        CoinStatus coinStatus = ValidationUtil.getCoin(450);
        System.out.println(coinStatus.printStatus());
        ;
    }
}
