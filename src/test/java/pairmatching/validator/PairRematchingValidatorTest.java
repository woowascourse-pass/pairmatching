package pairmatching.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PairRematchingValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void 공백만_입력되는_경우_예외를_발생_시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            PairRematchingValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"네니오", "아니요", "녜"})
    void 네_아니오를_제외한_다른_입력인_경우_예외를_발생_시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            PairRematchingValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }
}