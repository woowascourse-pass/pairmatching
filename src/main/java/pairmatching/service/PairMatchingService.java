package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.model.*;
import pairmatching.repository.CrewRepository;

import java.util.ArrayList;
import java.util.List;

import static pairmatching.view.InputView.ERROR_PREFIX;

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

    public Crew pairMatching(Course course, Crew backendCrew, Crew frontendCrew) {
        List<CrewMember> result = new ArrayList<>();
        if (course == Course.BACKEND) {
            result = backendCrew.getCrewMembers();
            return new Crew(Randoms.shuffle(result));
        }

        if (course == Course.FRONTEND) {
            result = frontendCrew.getCrewMembers();
            return new Crew(Randoms.shuffle(result));
        }

        throw new IllegalArgumentException(ERROR_PREFIX + "Unsupported course: " + course);
    }


}
