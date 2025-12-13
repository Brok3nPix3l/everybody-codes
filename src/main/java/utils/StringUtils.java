package main.java.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {
    public static List<String> splitInput(String input) {
        return Arrays.stream(input.split("\n")).toList();
    }
}
