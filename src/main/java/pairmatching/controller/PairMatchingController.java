package pairmatching.controller;

import java.util.List;
import pairmatching.dto.PairMatchingRequest;
import pairmatching.util.Parser;
import pairmatching.validator.FunctionValidator;
import pairmatching.validator.PairMatchingValidator;
import pairmatching.validator.PairRematchingValidator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    public void start() {
        while (true) {
            String answer = inputFunction();
            if (answer.equals("Q")) {
                return;
            }
            if (answer.equals("1")) {
                inputPairMatching();
            }
            if (answer.equals("2")) {

            }
            if (answer.equals("3")) {

            }
        }
    }

    private String inputFunction() {
        while (true) {
            try {
                String input = inputView.inputFunction();
                FunctionValidator.validate(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private PairMatchingRequest inputPairMatching() {
        while (true) {
            try {
                String input = inputView.inputPairMatching();
                PairMatchingValidator.validate(input);
                List<String> parsedInput = Parser.parseInput(input, ",");
                return new PairMatchingRequest(parsedInput.get(0), parsedInput.get(1),
                    parsedInput.get(2));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String inputPairRematching() {
        while (true) {
            try {
                String input = inputView.inputPairMatching();
                PairRematchingValidator.validate(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
