package e2024.quests;

import utils.StringUtils;

import java.awt.event.WindowStateListener;
import java.util.*;
import java.util.stream.Collectors;

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
        Set<String> words = new HashSet<>(Arrays.asList(lines.getFirst().split(":")[1].split(",")));
        List<String> reversedWords = new ArrayList<>();
        for (String word : words) {
            reversedWords.add(new StringBuilder(word).reverse().toString());
        }
        words.addAll(reversedWords);
        Set<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toSet());
        List<String> inscriptions = lines.subList(2,  lines.size());
        for (String inscription : inscriptions) {
            boolean[] charIsRunicSymbol =  new boolean[inscription.length()];
            for (int i = 0; i < inscription.length(); i++) {
                for (int length : wordLengths) {
                    if (i + length <= inscription.length() && words.contains(inscription.substring(i, i + length))) {
                        for (int j = i; j < i + length; j++) {
                            charIsRunicSymbol[j] = true;
                        }
                    }
                }
            }
            for (boolean charIsRunic : charIsRunicSymbol) {
                if (charIsRunic) {
                    ans++;
                }
            }
        }
        return ans;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
