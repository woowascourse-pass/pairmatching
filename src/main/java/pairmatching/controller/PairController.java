package pairmatching.controller;

import java.util.List;
import pairmatching.dto.PairMatchResult;
import pairmatching.message.ErrorMessage;
import pairmatching.service.PairService;
import pairmatching.util.Parser;
import pairmatching.validator.InputValidator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PairService pairService;

    public PairController(InputView inputView, OutputView outputView, PairService pairService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairService = pairService;
    }

    public void start() {
        String select;
        while (!((select = readFunction()).equals("Q"))) {

            int number = Parser.parseSelect(select);

            if (number == 1) {
                outputView.printPairInfo();
                pairMatching();
            }

            if (number == 2) {
                outputView.printPairInfo();
                retrievePair();
            }

            if (number == 3) {
                pairService.initializePair();
                outputView.clearPair();
            }
        }
    }

    private void retrievePair() {
        while (true) {
            PairMatchResult result = pairService.retrievePair(readPairInfo());
            if (!result.alreadyExist()) {
                outputView.printError(ErrorMessage.NO_PAIR_EXISTS.getMessage());
                continue;
            }
            outputView.printPair(result);
            return;
        }
    }

    private void pairMatching() {
        while (true) {
            boolean rematch = false;
            List<String> pairInfo = readPairInfo();
            PairMatchResult result = pairService.pairMatching(rematch, pairInfo);
            if (result.alreadyExist()) {
                rematch = readRematch();
            }

            if (rematch) {
                result = pairService.pairMatching(rematch, pairInfo);
            }
            // 아니오 선택시 여전히 result는 빈 값. 이때는 출력 X 다시 처음으로
            // 리매치 요청 X, 결과 빈값이면 다시 기능 선택 -> pairMatching() 메소드 자기자신...
            if (!rematch && result.alreadyExist()) {
                continue;
            }
            outputView.printPair(result);
            return;
        }
    }

    private boolean readRematch() {
        while (true) {
            try {
                return Parser.parseRematch(inputView.readRematch());
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private List<String> readPairInfo() {
        while(true) {
            try {
                String rawPairInfo = inputView.readPairInfo();
                List<String> pairInfo = Parser.parsePairInfo(rawPairInfo);
                InputValidator.validatePairInfo(pairInfo);
                return pairInfo;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private String readFunction() {
        while (true) {
            try {
                String selectedFunction = inputView.readFunction();
                return InputValidator.validateSelectedFunction(selectedFunction);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }


}
