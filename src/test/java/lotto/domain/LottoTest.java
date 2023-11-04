package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import lotto.domain.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 유효한 범위 밖에 있으면 예외가 발생한다.")
    @Test
    void createLottoByValidRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,-1)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또의 비교 기능이 정확한 값을 리턴하는지 검사한다")
    @Test
    void checkMatchedDigits() {
        // given
        Lotto lotto1 = new Lotto(new ArrayList<>(Arrays.asList(1,2,3,4,5,6)));
        Lotto lotto2 = new Lotto(new ArrayList<>(Arrays.asList(1,2,3,4,5,6)));
        // when
        // then
        assertThat(lotto1.match(lotto2)).isEqualTo(6);
    }
}