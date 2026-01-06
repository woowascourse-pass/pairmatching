package pairmatching.service;

import pairmatching.model.Course;
import pairmatching.model.Crew;
import pairmatching.repository.CrewRepository;

public class PairMatchingService {
    private final CrewRepository backendCrewRepository;
    private final CrewRepository frontendCrewRepository;

    public PairMatchingService(CrewRepository backendCrewRepository, CrewRepository frontendCrewRepository) {
        this.backendCrewRepository = backendCrewRepository;
        this.frontendCrewRepository = frontendCrewRepository;
    }

    public Crew loadBackendCrew() {
        return new Crew(backendCrewRepository.loadCrew(Course.BACKEND));
    }

    public Crew loadFrontendCrew() {
        return new Crew(frontendCrewRepository.loadCrew(Course.FRONTEND));
    }

    public void perform(String command) {
        // 페어 매칭


    }
}
