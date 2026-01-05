package pairmatching.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<String> parseInput(String input, String delimiter) {
        return new ArrayList<>(Arrays.asList(removeAllSpaces(input).split(delimiter, -1)));
    }

    public static String removeAllSpaces(String input) {
        return input.replaceAll("\\s", "");
    }
}
