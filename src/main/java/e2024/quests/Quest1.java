package e2024.quests;

import java.util.List;

import utils.StringUtils;

public class Quest1 extends Quest {

    @Override
    public long part1(String input, boolean debug) {
        long ans = 0L;
        if (debug) {
            System.out.println(input);
        }
        for (char c: input.toCharArray()) {
            switch (c) {
                case 'B':
                    ans++;
                    break;
                case 'C':
                    ans += 3;
                    break;
            }
        }
        return ans;
    }

    @Override
    public long part2(String input, boolean debug) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }

    @Override
    public long part3(String input, boolean debug) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
