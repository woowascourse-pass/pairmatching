package pairmatching.validator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PairMatchingValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void 공백만_입력되는_경우_예외를_발생_시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            PairMatchingValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"레벨1,백엔드,자동차경주", "자동차경주,레벨1,백엔드", "백엔드,자동차경주,레벨1"})
    void 입력_순서가_맞지_않으면_예외를_발생_시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            PairMatchingValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"백엔드,레벨6,자동차노선도", "백스타트,레벨1,자동차경주", "백엔드,레벨1,베포"})
    void 존재하지_않는_과정_레벨_미션이라면_예외를_발생_시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            PairMatchingValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"백엔드,6,자동차경주", "백엔드,레벨,자동차경주", "백엔드,레벨0,배포"})
    void 레벨의_문법이_맞지_않으면_예외를_발생_시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            PairMatchingValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"백엔드레벨3,자,동,차,경,주", "백엔드,레벨4자동차경주", "백엔드레벨1배포"})
    void 구분자를_적지_않으면_예외를_발생_시켜야_한다(String input) {
        // when & then
        assertThatThrownBy(() -> {
            PairMatchingValidator.validate(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }
}
