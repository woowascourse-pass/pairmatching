package pairmatching.model;

import java.util.List;

public class Crew {
    private final List<CrewMember> crewMembers;

    public Crew(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }
}