package pairmatching.config;

import pairmatching.controller.PairController;
import pairmatching.service.PairService;
import pairmatching.util.InputFileReader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class AppConfig {
    public PairController pairController() {
        return new PairController(inputView(), outputView(), pairService());
    }

    public PairService pairService() {
        return new PairService(inputFileReader());
    }

    public InputFileReader inputFileReader() {
        return new InputFileReader();
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
