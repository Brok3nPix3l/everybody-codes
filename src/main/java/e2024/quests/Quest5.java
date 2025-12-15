package e2024.quests;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        long ans = 0L;
        for (List<Integer> list : columns.values()) {
            ans *= 10;
            ans += list.getFirst();
        }
        return ans;
    }

    private void performRound(Map<Integer, List<Integer>> columns, int clapperColumnIndex) {
        int clapperNumber = columns.get(clapperColumnIndex).removeFirst();
        int targetColumnNumber = (clapperColumnIndex + 1) % columns.size();
        List<Integer> targetColumn = columns.get(targetColumnNumber);
        int insertionIndex = -1;
        for (int i = 0; i < clapperNumber; i++) {
            if (insertionIndex < targetColumn.size()) {
                insertionIndex++;
            } else {
                insertionIndex--;
            }
        }
        targetColumn.add(insertionIndex, clapperNumber);
        // for targetColumn.size() == 5
        // clapperNumber    | insertion index
        //------------------+-----------------
        // 1                | 0
        // 2                | 1
        // 3                | 2
        // 4                | 3
        // 5                | 4
        // 6                | 5
        // 7                | 4
        // 8                | 3
        // 9                | 2
        // 10               | 1
//        if (clapperNumber <= targetColumn.size() + 1) {
//            // insert after target element
//            targetColumn.add(clapperNumber - 1, clapperNumber);
//        } else {
//            // insert before target element
//            targetColumn.add(clapperNumber - (clapperNumber - targetColumn.size() + 1), clapperNumber);
//        }
         // System.out.println(columns);
    }

    private Map<Integer, List<Integer>> generateColumns(List<String> lines) {
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
