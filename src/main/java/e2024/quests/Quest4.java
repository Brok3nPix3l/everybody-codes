package e2024.quests;

import utils.StringUtils;

import java.util.List;

public class Quest4 extends QuestLong {

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
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        List<Long> sorted = lines.stream().map(Long::parseLong).sorted().toList();
        ans = computeStrikesRequiredToReachTargetHeight(sorted.get(sorted.size() / 2), sorted);
        return ans;
    }

    private long computeStrikesRequiredToReachTargetHeight(long target, List<Long> numbers) {
        long ans = 0L;
        for (Long number : numbers) {
            ans += Math.abs(number - target);
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
