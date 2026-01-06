package pairmatching.controller;

import pairmatching.model.Crew;
import pairmatching.model.Info;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.util.HashMap;
import java.util.Map;

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
        final Crew backendCrew = pairMatchingService.loadBackendCrew();
        final Crew frontendCrew = pairMatchingService.loadFrontendCrew();
        Map<Info, Crew> pairMatchingStorage = new HashMap<>();
        while (true) {
            // 기능 선택
            String command = readCommandUntilValid();
            // 페어 매칭
            if (command.equals("1")) {
                outputView.printInfo();
                // 과정, 레벨, 미션 입력
                Info info = readInfoUntilValid();
                // 매칭 정보 존재 여부 판단
                if (pairMatchingStorage.containsKey(info)) {
                    String whether = readWhetherUntilValid();
                    // 재매칭 안함
                    if (whether.equals("아니오")) {
                        continue;
                    }
                }
                Crew pairMatchingResult = pairMatchingService.pairMatching(info.course(), backendCrew, frontendCrew);
                pairMatchingStorage.put(info, pairMatchingResult);
                outputView.printPairMatchingResult(pairMatchingStorage.get(info));
                continue;
            }
            // 페어 조회
            if (command.equals("2")) {
                outputView.printInfo();
                // 과정, 레벨, 미션 입력
                Info info = readInfoUntilValid();
                outputView.printPairMatchingResult(pairMatchingStorage.get(info));
                continue;
            }
            // 페어 초기화
            if (command.equals("3")) {
                outputView.printClear();
                pairMatchingStorage.clear();
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

    private Info readInfoUntilValid() {
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
                return inputView.readWhether();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}