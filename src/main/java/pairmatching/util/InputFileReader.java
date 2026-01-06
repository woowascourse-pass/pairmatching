package pairmatching.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;

public class InputFileReader {

    public <T> List<T> readLines(String fileName, Function<String, T> mapper) {
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(fileName)) {

            validateInputStream(inputStream, fileName);

            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .map(mapper)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 중 오류가 발생했습니다.", e);
        }
    }

    private void validateInputStream(InputStream inputStream, String fileName) {
        if (inputStream == null) {
            throw new IllegalArgumentException("파일을 찾을 수 없습니다: " + fileName);
        }
    }
}
