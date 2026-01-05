package pairmatching.util;

import java.util.Arrays;
import java.util.List;
import pairmatching.message.ErrorMessage;

public class Parser {

    public static int parseSelect(String select) {
        try{
            return Integer.parseInt(select);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    public static List<String> parsePairInfo(String pairInfo) {
        return Arrays.stream(pairInfo.split(","))
                .map(String::trim)
                .toList();
    }

    public static boolean parseRematch(String rematch) {
        if (rematch.equals("네")) {
            return true;
        }

        if (rematch.equals("아니오")) {
            return false;
        }

        throw new IllegalArgumentException(ErrorMessage.INVALID_REMATCH.getMessage());
    }
}
