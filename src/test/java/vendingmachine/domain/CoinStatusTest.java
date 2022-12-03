package vendingmachine.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CoinStatusTest {

    @Test
    void 자판기가_보유하고_있는_금액으로부터_coin을_추출한다() {
        CoinStatus coinStatus = CoinStatus.create(450);
        assertThat(coinStatus.isSameAmountTotal(450)).isTrue();
    }

}