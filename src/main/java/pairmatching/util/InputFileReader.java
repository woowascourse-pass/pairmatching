package pairmatching.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class InputFileReader {

    public List<Crew> readFront() {
        try {
            // 파일 입력스트림 생성
            FileReader fileReader = new FileReader("src/main/resources/frontend-crew.md");
            // 입력 버퍼 생성
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<Crew> frontCrews = new ArrayList<>();
            String name;
            while((name = bufferedReader.readLine()) != null) {
                frontCrews.add(new Crew(Course.FRONTEND, name));
            }
            return frontCrews;
        } catch (IOException e) {
            System.out.println("잘못된 입력입니다.");
        }
        return List.of();
    }

    public List<Crew> readBack() {
        try {
            // 파일 입력스트림 생성
            FileReader fileReader = new FileReader("src/main/resources/backend-crew.md");
            // 입력 버퍼 생성
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<Crew> backCrews = new ArrayList<>();
            String name;
            while((name = bufferedReader.readLine()) != null) {
                backCrews.add(new Crew(Course.BACKEND, name));
            }

            return backCrews;
        } catch (IOException e) {
            System.out.println("잘못된 입력입니다.");
        }
        return List.of();
    }
}
