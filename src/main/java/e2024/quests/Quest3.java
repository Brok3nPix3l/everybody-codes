package e2024.quests;

import utils.StringUtils;

import java.util.*;

public class Quest3 extends Quest {
    private static final char SAFE = '#';

    @Override
    public long part1(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        // can safely remove the first layer without issue
        Set<List<Integer>> safeCoords = new HashSet<>();
        for (int r = 0; r < lines.size(); r++) {
            String row = lines.get(r);
            for (int c = 0; c < row.length(); c++) {
                if (row.charAt(c) == SAFE) {
                    ans++;
                    safeCoords.add(List.of(r, c));
                }
            }
        }
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Set<List<Integer>> nextSafeCoords;
        do {
            nextSafeCoords = new HashSet<>();
            for (int r = 0; r < lines.size(); r++) {
                String row = lines.get(r);
                for (int c = 0; c < row.length(); c++) {
                    if (row.charAt(c) != SAFE) {
                        continue;
                    }
                    boolean surroundedBySafeSpaces = true;
                    for (int[] direction : directions) {
                        if (!safeCoords.contains(List.of(r + direction[0], c + direction[1]))) {
                            surroundedBySafeSpaces = false;
                            break;
                        }
                    }
                    if (surroundedBySafeSpaces) {
                        nextSafeCoords.add(List.of(r, c));
                        ans++;
                    }
                }
            }
            safeCoords = new HashSet<>(nextSafeCoords);
        } while (!nextSafeCoords.isEmpty());
        return ans;
    }

    @Override
    public long part2(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        // can safely remove the first layer without issue
        Set<List<Integer>> safeCoords = new HashSet<>();
        for (int r = 0; r < lines.size(); r++) {
            String row = lines.get(r);
            for (int c = 0; c < row.length(); c++) {
                if (row.charAt(c) == SAFE) {
                    ans++;
                    safeCoords.add(List.of(r, c));
                }
            }
        }
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Set<List<Integer>> nextSafeCoords;
        do {
            nextSafeCoords = new HashSet<>();
            for (int r = 0; r < lines.size(); r++) {
                String row = lines.get(r);
                for (int c = 0; c < row.length(); c++) {
                    if (row.charAt(c) != SAFE) {
                        continue;
                    }
                    boolean surroundedBySafeSpaces = true;
                    for (int[] direction : directions) {
                        if (!safeCoords.contains(List.of(r + direction[0], c + direction[1]))) {
                            surroundedBySafeSpaces = false;
                            break;
                        }
                    }
                    if (surroundedBySafeSpaces) {
                        nextSafeCoords.add(List.of(r, c));
                        ans++;
                    }
                }
            }
            safeCoords = new HashSet<>(nextSafeCoords);
        } while (!nextSafeCoords.isEmpty());
        return ans;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
