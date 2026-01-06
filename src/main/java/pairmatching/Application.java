package pairmatching;


import pairmatching.controller.PairMatchingController;
import pairmatching.repository.CrewRepository;
import pairmatching.service.PairMatchingService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    private static final String BACKEND_CREW_FILE_PATH = "backend-crew.md";
    private static final String FRONTEND_CREW_FILE_PATH = "frontend-crew.md";

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        CrewRepository backendCrewRepository = new CrewRepository(BACKEND_CREW_FILE_PATH);
        CrewRepository frontendCrewRepository = new CrewRepository(FRONTEND_CREW_FILE_PATH);
        PairMatchingService pairMatchingService = new PairMatchingService(backendCrewRepository, frontendCrewRepository);

        PairMatchingController pairMatchingController = new PairMatchingController(inputView, outputView, pairMatchingService);
        pairMatchingController.run();
    }
}
