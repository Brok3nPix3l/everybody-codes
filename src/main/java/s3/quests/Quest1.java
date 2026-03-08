package s3.quests;

import utils.StringUtils;

import java.util.List;

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
        return 0;
    }

    @Override
    public long part3(String input) {
        return 0;
    }
}
