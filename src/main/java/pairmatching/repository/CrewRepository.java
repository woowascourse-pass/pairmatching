package pairmatching.repository;

import pairmatching.model.Course;
import pairmatching.model.CrewMember;
import pairmatching.util.ResourceReader;
import pairmatching.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrewRepository {
    private final String resourcePath;

    public CrewRepository(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public List<CrewMember> loadCrew(Course course) {
        List<CrewMember> crewMembers = new ArrayList<>();

        try (BufferedReader bufferedReader = ResourceReader.getBufferedReader(resourcePath)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                crewMembers.add(parseCrew(line, course));
            }
        } catch (IOException e) {
            throw new IllegalStateException("[ERROR] 파일 읽는 중 오류 발생: " + resourcePath, e);
        }
        return crewMembers;
    }

    private CrewMember parseCrew(String line, Course course) {
        List<String> parts = Parser.parseByDelimiter(line);
        if (parts.size() != 1) {
            throw new IllegalStateException("[ERROR] 파일 형식 오류");
        }

        String name = parts.get(0);
        return new CrewMember(course, name);
    }
}
