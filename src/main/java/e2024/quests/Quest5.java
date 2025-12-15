package e2024.quests;

import utils.StringUtils;

import java.util.*;

public class Quest5 extends Quest {

    public long performRounds(String input, int roundCount) {
        List<String> lines = StringUtils.splitInput(input);
        Map<Integer, List<Integer>> columns = generateColumns(lines);
        int clapperColumnIndex = 0;
        // System.out.println("Performing " + roundCount + " round(s)");
        for (int i = 0; i < roundCount; i++) {
            performRound(columns, clapperColumnIndex);
            clapperColumnIndex = (clapperColumnIndex + 1) % columns.size();
        }
        return firstPersonInEachColumnNumber(columns);
    }

    private long firstPersonInEachColumnNumber(Map<Integer, List<Integer>> columns) {
        List<String> firstNumbers = new ArrayList<>();
        for (List<Integer> list : columns.values()) {
            firstNumbers.add(String.valueOf(list.getFirst()));
        }
        return Long.parseLong(String.join("", firstNumbers));
    }

    public void performRound(Map<Integer, List<Integer>> columns, int clapperColumnIndex) {
        int clapperNumber = columns.get(clapperColumnIndex).removeFirst();
        int targetColumnNumber = (clapperColumnIndex + 1) % columns.size();
        List<Integer> targetColumn = columns.get(targetColumnNumber);
        // for targetColumn.size() == 2
        // clapperNumber    | insertion index
        //------------------+-----------------
        // 1                | 0
        // 2                | 1
        // 3                | 2
        // 4                | 1
        // 5                | 0
        // 6                | 1
        // 7                | 2
        // 8                | 1
        // 9                | 0
        // 10               | 1

        // targetColumn.size() * 2 - 1 unique positions
        // 0, 1, 2, 3, 4, 5, 4, 3, 2, and 1
        int insertionIndex;
        int clapperNumberMod = (clapperNumber - 1) % (targetColumn.size() * 2) + 1;
        if (clapperNumberMod <= targetColumn.size()) {
            // insert after target element
            insertionIndex = clapperNumberMod - 1;
        } else {
            // insert before target element
            insertionIndex = targetColumn.size() * 2 - (clapperNumberMod) + 1;
        }
        targetColumn.add(insertionIndex, clapperNumber);
    }

    public Map<Integer, List<Integer>> generateColumns(List<String> lines) {
        Map<Integer, List<Integer>> columns = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" ");
            for (int i = 0; i < parts.length; i++) {
                columns.computeIfAbsent(i, _ -> new ArrayList<>()).add(Integer.parseInt(parts[i]));
            }
        }
        return columns;
    }

    @Override
    public long part1(String input) {
        return performRounds(input, 10);
    }

    @Override
    public long part2(String input) {
        return performRoundsUntilANumberIsShoutedTimes(input, 2024);
    }

    private long performRoundsUntilANumberIsShoutedTimes(String input, int shoutTimes) {
        List<String> lines = StringUtils.splitInput(input);
        Map<Integer, List<Integer>> columns = generateColumns(lines);
        Map<Long, Integer> timesShouted = new HashMap<>();
        int maxTimesShouted = 0;
        int clapperColumnIndex = 0;
        long roundCount = 0L;
        long numberShouted = 0;
        // System.out.println("Performing " + roundCount + " round(s)");
        while (maxTimesShouted < shoutTimes) {
            performRound(columns, clapperColumnIndex);
            clapperColumnIndex = (clapperColumnIndex + 1) % columns.size();
            numberShouted = firstPersonInEachColumnNumber(columns);
            timesShouted.put(numberShouted, timesShouted.getOrDefault(numberShouted, 0) + 1);
            maxTimesShouted = Math.max(timesShouted.get(numberShouted), maxTimesShouted);
            roundCount++;
        }
//        System.out.println(timesShouted.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator
//        .reverseOrder())).toList());
        return numberShouted * roundCount;
    }

    @Override
    public long part3(String input) {
        return maxNumberShoutedWhilePerformRoundsForever(input);
    }

    private long maxNumberShoutedWhilePerformRoundsForever(String input) {
        Set<Long> seenShoutedNumbers = new HashSet<>();
        List<String> lines = StringUtils.splitInput(input);
        Map<Integer, List<Integer>> columns = generateColumns(lines);
        int clapperColumnIndex = 0;
        int roundCount = 0;
        long maxNumberShouted = Long.MIN_VALUE;
        for (int i = 0; i < 50000; i++) {
            performRound(columns, clapperColumnIndex);
            clapperColumnIndex = (clapperColumnIndex + 1) % columns.size();
            long numberShouted = firstPersonInEachColumnNumber(columns);
            if (!seenShoutedNumbers.contains(numberShouted)) {
                System.out.println(
                        "on round " + roundCount + " " + numberShouted + " was shouted for the first time");
                seenShoutedNumbers.add(numberShouted);
                maxNumberShouted = Math.max(maxNumberShouted, numberShouted);
            }
            roundCount++;
        }
        return maxNumberShouted;
    }
}
