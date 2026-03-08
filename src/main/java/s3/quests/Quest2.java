package s3.quests;

import utils.StringUtils;

import java.util.*;

public class Quest2 extends QuestLong {
    final char HEAD = '@';
    final char VOCAL_BONE = '#';
    final char EMPTY = '.';

    @Override
    public long part1(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        final int HEIGHT = lines.size();
        final int WIDTH = lines.getFirst().length();
        Map<List<Integer>, Character> grid = new HashMap<>();
        List<Integer> curPos = findChar(HEAD, lines);
        grid.put(curPos, HEAD);
        List<Integer> vocalBone = findChar(VOCAL_BONE, lines);
        grid.put(vocalBone, VOCAL_BONE);
        List<List<Integer>> directions = List.of(
                List.of(-1,0),
                List.of(0,1),
                List.of(1,0),
                List.of(0,-1)
        );
        int curDirection = 0;
        int steps = 0;
        while (!curPos.equals(vocalBone)) {
            List<Integer> direction = directions.get(curDirection);
            List<Integer> targetPos = List.of((curPos.getFirst() + direction.getFirst()) % WIDTH, (curPos.getLast() + direction.getLast()) % HEIGHT);
            while (!attemptMoveToPos(targetPos, grid)) {
                curDirection = (curDirection + 1) % directions.size();
                direction = directions.get(curDirection);
                targetPos = List.of((curPos.getFirst() + direction.getFirst()) % WIDTH, (curPos.getLast() + direction.getLast()) % HEIGHT);
            }
            curDirection = (curDirection + 1) % directions.size();
            curPos = targetPos;
            steps++;
        }
        return steps;
    }

    private boolean attemptMoveToPos(List<Integer> targetPos, Map<List<Integer>, Character> grid) {
        if (grid.containsKey(targetPos) && grid.get(targetPos) != VOCAL_BONE) {
            return false;
        } else {
            grid.put(targetPos, HEAD);
            return true;
        }
    }

    private List<Integer> findChar(char target, List<String> lines) {
        List<Integer> ans = new ArrayList<>();
        outer:
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == target) {
                    ans.add(i);
                    ans.add(j);
                    break outer;
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
