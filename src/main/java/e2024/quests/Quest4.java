package e2024.quests;

import utils.StringUtils;

import java.util.List;

public class Quest4 extends Quest {

    @Override
    public long part1(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        long min = Long.MAX_VALUE;
        for (String line : lines) {
            min = Math.min(min, Long.parseLong(line));
        }
        for (String line : lines) {
            ans += Long.parseLong(line) - min;
        }
        return ans;
    }

    @Override
    public long part2(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        long min = Long.MAX_VALUE;
        for (String line : lines) {
            min = Math.min(min, Long.parseLong(line));
        }
        for (String line : lines) {
            ans += Long.parseLong(line) - min;
        }
        return ans;
    }

    @Override
    public long part3(String input) {
        long ans = Long.MAX_VALUE;
        List<String> lines = StringUtils.splitInput(input);
        MinAndMaxValues minAndMaxValues = determineMinAndMaxValues(lines);
        long l = minAndMaxValues.min(), r = minAndMaxValues.max();
        for (long target = l; target <= r; target++) {
            long curStrikesRequiredToReachTargetHeight = 0L;
            for (String line : lines) {
                curStrikesRequiredToReachTargetHeight += Math.abs(Long.parseLong(line) - target);
            }
            ans = Math.min(ans, curStrikesRequiredToReachTargetHeight);
        }
        return ans;
    }

    private MinAndMaxValues determineMinAndMaxValues(List<String> lines) {
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for (String line : lines) {
            long cur = Long.parseLong(line);
            min = Math.min(min, cur);
            max = Math.max(max, cur);
        }
        return new MinAndMaxValues(min, max);
    }


    private record MinAndMaxValues(long min, long max) {};
}
