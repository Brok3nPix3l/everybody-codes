package s3.quests;

import utils.StringUtils;

import java.util.*;

public class Quest2 extends QuestLong {
    final char HEAD = '@';
    final char VOCAL_BONE = '#';
    final List<List<Integer>> DIRECTIONS = List.of(
            List.of(-1,0),
            List.of(0,1),
            List.of(1,0),
            List.of(0,-1)
    );

    @Override
    public long part1(String input) {
        List<String> lines = StringUtils.splitInput(input);
        final int HEIGHT = lines.size();
        final int WIDTH = lines.getFirst().length();
        Map<List<Integer>, Character> grid = new HashMap<>();
        List<Integer> curPos = findChar(HEAD, lines);
        grid.put(curPos, HEAD);
        List<Integer> vocalBone = findChar(VOCAL_BONE, lines);
        grid.put(vocalBone, VOCAL_BONE);
        int curDirection = 0;
        int steps = 0;
        while (!curPos.equals(vocalBone)) {
            List<Integer> direction = DIRECTIONS.get(curDirection);
            List<Integer> targetPos = List.of((curPos.getFirst() + direction.getFirst()) % WIDTH, (curPos.getLast() + direction.getLast()) % HEIGHT);
            while (!attemptMoveToPos(targetPos, grid)) {
                curDirection = (curDirection + 1) % DIRECTIONS.size();
                direction = DIRECTIONS.get(curDirection);
                targetPos = List.of((curPos.getFirst() + direction.getFirst()) % WIDTH, (curPos.getLast() + direction.getLast()) % HEIGHT);
            }
            curDirection = (curDirection + 1) % DIRECTIONS.size();
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

    private boolean attemptMoveToPosAndRespectVocalBone(List<Integer> targetPos, Map<List<Integer>, Character> grid) {
        if (grid.containsKey(targetPos)) {
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
        List<String> lines = StringUtils.splitInput(input);
        final int HEIGHT = lines.size();
        final int WIDTH = lines.getFirst().length();
        Map<List<Integer>, Character> grid = new HashMap<>();
        List<Integer> curPos = findChar(HEAD, lines);
        grid.put(curPos, HEAD);
        List<Integer> vocalBone = findChar(VOCAL_BONE, lines);
        grid.put(vocalBone, VOCAL_BONE);
        int curDirection = 0;
        int steps = 0;
        while (!surrounded(vocalBone, grid, WIDTH, HEIGHT)) {
            List<Integer> direction = DIRECTIONS.get(curDirection);
            List<Integer> targetPos = List.of((curPos.getFirst() + direction.getFirst()) % WIDTH, (curPos.getLast() + direction.getLast()) % HEIGHT);
            while (!attemptMoveToPosAndRespectVocalBone(targetPos, grid)) {
                curDirection = (curDirection + 1) % DIRECTIONS.size();
                direction = DIRECTIONS.get(curDirection);
                targetPos = List.of((curPos.getFirst() + direction.getFirst()) % WIDTH, (curPos.getLast() + direction.getLast()) % HEIGHT);
            }
            curDirection = (curDirection + 1) % DIRECTIONS.size();
            curPos = targetPos;
            steps++;
        }
        return steps;
    }

    private boolean surrounded(List<Integer> vocalBone, Map<List<Integer>, Character> grid, int width,
                               int height) {
        List<List<Integer>> visited = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(vocalBone);
        while (!queue.isEmpty()) {
            List<Integer> cur = queue.poll();
            if (cur.getFirst() == 0 || cur.getFirst() == width - 1 || cur.getLast() == 0 || cur.getLast() == height - 1) {
                return false;
            }
            if (visited.contains(cur)) {
                continue;
            }
            visited.add(cur);
            if (grid.containsKey(cur) && grid.get(cur) != VOCAL_BONE) {
                continue;
            }
            for (List<Integer> direction : DIRECTIONS) {
                queue.add(List.of(cur.getFirst() + direction.getFirst(), cur.getLast() + direction.getLast()));
            }
        }
        return true;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
