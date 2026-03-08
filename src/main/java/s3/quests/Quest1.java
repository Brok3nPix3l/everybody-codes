package s3.quests;

import utils.StringUtils;

import java.util.*;

public class Quest1 extends QuestLong {

    @Override
    public long part1(String input) {
        List<String> scales = StringUtils.splitInput(input);
        int greenDominantScalesIdentifierSum = 0;
        for (String scale : scales) {
            String[] split = scale.split(":");
            int decimal = Integer.parseInt(split[0]);
            split = split[1].split(" ");
            String red = split[0];
            String green = split[1];
            String blue = split[2];
            String redBinaryString = red.replaceAll("r", "0").replaceAll("R", "1");
            String greenBinaryString = green.replaceAll("g", "0").replaceAll("G", "1");
            String blueBinaryString = blue.replaceAll("b", "0").replaceAll("B", "1");
            int redIntDecimal = Integer.parseInt(redBinaryString, 2);
            int greenIntDecimal = Integer.parseInt(greenBinaryString, 2);
            int blueIntDecimal = Integer.parseInt(blueBinaryString, 2);
            if (greenIntDecimal > redIntDecimal && greenIntDecimal > blueIntDecimal) {
                greenDominantScalesIdentifierSum += decimal;
            }
        }
        return greenDominantScalesIdentifierSum;
    }

    @Override
    public long part2(String input) {
        List<String> scales = StringUtils.splitInput(input);
        int lowestColorSumIndetifier = 0;
        int highestShine = 0;
        int lowestColorSum = Integer.MAX_VALUE;
        for (String scale : scales) {
            String[] split = scale.split(":");
            int decimal = Integer.parseInt(split[0]);
            split = split[1].split(" ");
            String red = split[0];
            String green = split[1];
            String blue = split[2];
            String shine = split[3];
            String redBinaryString = red.replaceAll("r", "0").replaceAll("R", "1");
            String greenBinaryString = green.replaceAll("g", "0").replaceAll("G", "1");
            String blueBinaryString = blue.replaceAll("b", "0").replaceAll("B", "1");
            String shineBinaryString = shine.replaceAll("s", "0").replaceAll("S", "1");
            int redIntDecimal = Integer.parseInt(redBinaryString, 2);
            int greenIntDecimal = Integer.parseInt(greenBinaryString, 2);
            int blueIntDecimal = Integer.parseInt(blueBinaryString, 2);
            int colorSum = redIntDecimal + greenIntDecimal + blueIntDecimal;
            int shineIntDecimal = Integer.parseInt(shineBinaryString, 2);
            if (shineIntDecimal > highestShine) {
                highestShine = shineIntDecimal;
                lowestColorSum = colorSum;
                lowestColorSumIndetifier = decimal;
            } else if (shineIntDecimal == highestShine) {
                if (colorSum < lowestColorSum) {
                    lowestColorSum = colorSum;
                    lowestColorSumIndetifier = decimal;
                }
            }
        }
        return lowestColorSumIndetifier;
    }

    @Override
    public long part3(String input) {
        final int MATTE_THRESHOLD = 30;
        final int SHINY_THRESHOLD = 33;
        final String MATTE = "matte";
        final String SHINY = "shiny";
        final String RED = "red";
        final String GREEN = "green";
        final String BLUE = "blue";
        List<String> scales = StringUtils.splitInput(input);
        // key is ["red"/"green"/"blue", "matte"/"shiny"]
        // value is list of scale identifiers
        Map<List<String>,List<Integer>> scaleGroups = new HashMap<>();
        for (String scale : scales) {
            String[] split = scale.split(":");
            int decimal = Integer.parseInt(split[0]);
            split = split[1].split(" ");
            String red = split[0];
            String green = split[1];
            String blue = split[2];
            String shine = split[3];
            String redBinaryString = red.replaceAll("r", "0").replaceAll("R", "1");
            String greenBinaryString = green.replaceAll("g", "0").replaceAll("G", "1");
            String blueBinaryString = blue.replaceAll("b", "0").replaceAll("B", "1");
            String shineBinaryString = shine.replaceAll("s", "0").replaceAll("S", "1");
            int redIntDecimal = Integer.parseInt(redBinaryString, 2);
            int greenIntDecimal = Integer.parseInt(greenBinaryString, 2);
            int blueIntDecimal = Integer.parseInt(blueBinaryString, 2);
            int shineIntDecimal = Integer.parseInt(shineBinaryString, 2);
            String shinyIndentifier = null;
            if (shineIntDecimal <= MATTE_THRESHOLD) {
                shinyIndentifier = MATTE;
            } else if (shineIntDecimal >= SHINY_THRESHOLD) {
                shinyIndentifier = SHINY;
            } else {
            // too shiny to be matte, not shiny enough to be shiny; indeterminate
                continue;
            }
            String colorIndentifier = null;
            if (redIntDecimal > greenIntDecimal && redIntDecimal > blueIntDecimal) {
                colorIndentifier = RED;
            } else if (greenIntDecimal > redIntDecimal && greenIntDecimal > blueIntDecimal) {
                colorIndentifier = GREEN;
            } else if (blueIntDecimal > redIntDecimal && blueIntDecimal > greenIntDecimal) {
                colorIndentifier = BLUE;
            } else {
                // no specific color is dominant; indeterminate
                continue;
            }
            scaleGroups.computeIfAbsent(List.of(shinyIndentifier, colorIndentifier), _ -> new ArrayList<>()).add(decimal);
        }
        Map.Entry<List<String>, List<Integer>> largestGroup = scaleGroups.entrySet().stream().max(Comparator.comparingInt(e -> e.getValue().size())).orElseThrow();
        return largestGroup.getValue().stream().reduce(0, Integer::sum);
    }
}
