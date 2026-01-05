package pairmatching.controller;

import java.util.List;
import pairmatching.domain.Pair;
import pairmatching.dto.PairMatchingRequest;
import pairmatching.service.PairMatchingService;
import pairmatching.util.Parser;
import pairmatching.validator.FunctionValidator;
import pairmatching.validator.PairMatchingValidator;
import pairmatching.validator.PairRematchingValidator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {

    private PairMatchingService pairMatchingService = new PairMatchingService();
    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    public void start() {
        while (true) {
            String answer = inputFunction();
            if (answer.equals("Q")) {
                return;
            }
            if (answer.equals("1")) {
                PairMatchingRequest request = inputPairMatching();
                try {
                    if (request != null) {
                        List<Pair> pairs = pairMatchingService.matchPair(request);
                        outputView.printPairMatchingResult(pairs);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (answer.equals("2")) {
                PairMatchingRequest request = inputPrintPairMatching();
                List<Pair> pairs = pairMatchingService.findPairs(request);
                if (pairs.isEmpty()) {
                    System.out.println("[ERROR] 매칭 이력이 없습니다.");
                    continue;
                }
                outputView.printPairMatchingResult(pairs);
            }
            if (answer.equals("3")) {
                pairMatchingService.initPairManager();
                outputView.printInitialMessage();
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
        outputView.printCourseMessage();
        while (true) {
            try {
                String input = inputView.inputPairMatching();
                PairMatchingValidator.validate(input);
                List<String> parsedInput = Parser.parseInput(input, ",");
                PairMatchingRequest request = new PairMatchingRequest(parsedInput.get(0),
                    parsedInput.get(1),
                    parsedInput.get(2));
                if (!pairMatchingService.isNoMatching(request)) {
                    String answer = inputPairRematching();
                    if (answer.equals("아니오")) {
                        return null;
                    }
                }
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
                String input = inputView.inputPairRematching();
                PairRematchingValidator.validate(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private PairMatchingRequest inputPrintPairMatching() {
        outputView.printCourseMessage();
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
}
