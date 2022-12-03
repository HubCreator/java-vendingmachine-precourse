package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CoinStatusTest {

    @Test
    void 자판기가_보유하고_있는_금액으로부터_coin을_추출한다() {
        CoinStatus coinStatus = CoinStatus.create(450);
        assertThat(coinStatus.isSameAmountTotal(450)).isTrue();
    }

    @RepeatedTest(50)
    void name() {
        int result = Randoms.pickNumberInList(Arrays.asList(0, 1, 2, 3));
        assertThat(result).isGreaterThanOrEqualTo(0);
        assertThat(result).isLessThanOrEqualTo(3);
    }
}