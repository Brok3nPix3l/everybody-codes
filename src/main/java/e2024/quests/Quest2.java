package e2024.quests;

import utils.StringUtils;

import java.awt.event.WindowStateListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Quest2 extends Quest {

    @Override
    public long part1(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        Set<String> words = new HashSet<>(List.of(lines.getFirst().split(":")[1].split(",")));
        String inscription = lines.get(2);
        for (int i = 0; i < inscription.length(); i++) {
            int finalI = i;
            for (String potentialWord : words.stream().filter(w -> w.charAt(0) == inscription.charAt(finalI) && inscription.length() -
                    finalI >= w.length()).toList()) {
                if (inscription.startsWith(potentialWord, i)) {
                    ans++;
                }
            }
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
