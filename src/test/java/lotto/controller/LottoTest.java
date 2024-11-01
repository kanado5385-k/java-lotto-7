package lotto.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.enums.ErrorMessage;
import lotto.model.Lotto;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_QUANTITY.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
    }

    @Test
    void 제한_범위를_벗어난_번호가_있다면_예외가_발생한다(){
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,67)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBER.getMessage());
    }

    @Test
    void 정상적인_로또_번호응_예외가_발행하지_않는다(){
        assertThatCode(() -> new Lotto(List.of(1,2,3,4,5,6)))
                .doesNotThrowAnyException();
    }

    @Test
    void 정상적인_로또_번호를_문자열로_변환(){
        List<Integer> numbers = Arrays.asList(5, 1, 3, 2, 4, 6);
        Lotto lotto = new Lotto(numbers);
        String expectedOutput = "[1, 2, 3, 4, 5, 6]";

        assertThat(lotto.sortedNumbersToString()).isEqualTo(expectedOutput);
    }
}