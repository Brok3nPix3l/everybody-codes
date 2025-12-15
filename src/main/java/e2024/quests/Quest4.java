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
        return ans;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
