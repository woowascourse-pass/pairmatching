package pairmatching.controller;

import pairmatching.model.Crew;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    public PairMatchingController(InputView inputView, OutputView outputView, PairMatchingService pairMatchingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatchingService = pairMatchingService;
    }

    public void run() {
        // 크루 파일 읽기
        Crew backendCrew = pairMatchingService.loadBackendCrew();
        Crew frontendCrew = pairMatchingService.loadFrontendCrew();
        while (true) {
            // 기능 선택
            String command = readCommandUntilValid();
            Crew pairMatchingResult;
            // 페어 매칭
            if (command.equals("1")) {
                outputView.printInfo();
                // 매칭 정보 존재
                if () {
                    String whether = readWhetherUntilValid();

                    // 재매칭 안함
                    if (!whether) {
                        // 다시 기능 선택으로 돌아가기
                        continue;
                    }
                }

                // 과정, 레벨, 미션 입력
                String info = readInfoUntilValid();
                pairMatchingResult = pairMatchingService.pairMatching();
                outputView.printPairMatchingResult(pairMatchingResult);
                continue;
            }
            // 페어 조회
            if (command.equals("2")) {
                outputView.printInfo();
                // 과정, 레벨, 미션 입력
                String info = readInfoUntilValid();
                outputView.printPairMatchingResult(pairMatchingResult);
                continue;
            }
            // 페어 초기화
            if (command.equals("3")) {
                outputView.printClear();
                pairMatchingResult.clear();
                continue;
            }
            // 종료
            if (command.equals("Q")) {
                break;
            }
        }

    }

    private String readCommandUntilValid() {
        while (true) {
            try {
                return inputView.readCommand();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private String readInfoUntilValid() {
        while (true) {
            try {
                return inputView.readInfo();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private String readWhetherUntilValid() {
        while (true) {
            try {
                return inputView.readCommand();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}