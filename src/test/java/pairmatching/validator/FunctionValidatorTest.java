package pairmatching.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FunctionValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void 공백만_입력되는_경우_예외를_발생_시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            FunctionValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "4", "q", "W", "1Q"})
    void 사용자의_입력값이_1_2_3_Q가_아니라면_예외를_발생시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            FunctionValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }
}