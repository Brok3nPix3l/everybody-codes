package e2024.quests;

import java.util.List;

import utils.StringUtils;

public class Quest1 extends Quest {

    @Override
    public long part1(String input) {
        long ans = 0L;
        for (char c: input.toCharArray()) {
            ans += getPotionsRequiredForCreature(c);
        }
        return ans;
    }

    private static long getPotionsRequiredForCreature(char c) {
        return switch (c) {
            case 'B' -> 1;
            case 'C' -> 3;
            case 'D' -> 5;
            default -> 0;
        };
    }

    @Override
    public long part2(String input) {
        long ans = 0L;
        for (int i = 0; i < input.length(); i += 2) {
            long localPotionCount = 0L;
            int creatureCountInPair = 0;
            for (char c : List.of(input.charAt(i), input.charAt(i + 1))) {
                localPotionCount += getPotionsRequiredForCreature(c);
                if (isCreature(c)) {
                    creatureCountInPair++;
                }
            }
            ans += localPotionCount;
            if (creatureCountInPair == 2) {
                ans += 2;
            }
        }
        return ans;
    }

    private boolean isCreature(char c) {
        return 'A' <= c && c <= 'D';
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
