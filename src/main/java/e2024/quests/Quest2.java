package e2024.quests;

import utils.StringUtils;

import java.awt.event.WindowStateListener;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Quest2 extends QuestLong {

    @Override
    public long part1(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        Set<String> words = new HashSet<>(List.of(lines.getFirst().split(":")[1].split(",")));
        String inscription = lines.get(2);
        for (int i = 0; i < inscription.length(); i++) {
            int finalI = i;
            for (String potentialWord : words.stream().filter(w -> w.charAt(0) == inscription.charAt(finalI) &&
                    inscription.length() - finalI >= w.length()).toList()) {
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
        List<String> inscriptions = lines.subList(2, lines.size());
        for (String inscription : inscriptions) {
            boolean[] charIsRunicSymbol = new boolean[inscription.length()];
            for (int i = 0; i < inscription.length(); i++) {
                for (int length : wordLengths) {
                    if (i + length <= inscription.length() &&
                            words.contains(inscription.substring(i, i + length))) {
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
        Set<String> words = new HashSet<>(Arrays.asList(lines.getFirst().split(":")[1].split(",")));
        List<String> reversedWords = new ArrayList<>();
        for (String word : words) {
            reversedWords.add(new StringBuilder(word).reverse().toString());
        }
        words.addAll(reversedWords);
        Set<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toSet());
        List<String> inscriptions = lines.subList(2, lines.size());
        boolean[][] charIsRunicSymbol = new boolean[inscriptions.size()][inscriptions.getFirst().length()];
        for (int row = 0; row < inscriptions.size(); row++) {
            String inscription = inscriptions.get(row);
            for (int i = 0; i < inscription.length(); i++) {
                for (int length : wordLengths) {
                    StringBuilder sb = new StringBuilder();
                    if (i + length >= inscription.length()) {
                        sb.append(inscription.substring(i));
                        sb.append(inscription, 0, length - (inscription.length() - i));
                    } else {
                        sb.append(inscription, i, i + length);
                    }
                    if (words.contains(sb.toString())) {
                        for (int j = i; j < i + length; j++) {
                            charIsRunicSymbol[row][j % inscription.length()] = true;
                        }
                    }
                }
            }
        }
        // assumption: all inscriptions will have the same length
        for (int column = 0; column < inscriptions.getFirst().length(); column++) {
            for (int i = 0; i < inscriptions.size(); i++) {
                for (int length : wordLengths) {
                    if (i + length > inscriptions.size()) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int j = i; j < i + length; j++) {
                        sb.append(inscriptions.get(j % inscriptions.size()).charAt(column));
                    }
                    if (words.contains(sb.toString())) {
                        for (int j = i; j < i + length; j++) {
                            charIsRunicSymbol[j % inscriptions.size()][column] = true;
                        }
                    }
                }
            }
        }
        for (boolean[] row : charIsRunicSymbol) {
            for (boolean charIsRunic : row) {
                if (charIsRunic) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
