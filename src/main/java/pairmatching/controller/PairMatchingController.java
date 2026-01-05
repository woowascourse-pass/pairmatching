package pairmatching.controller;

import pairmatching.validator.FunctionValidator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    public void start() {

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
}
