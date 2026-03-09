package s3.quests;

import utils.StringUtils;

import java.util.*;

public class Quest2 extends QuestLong {
    final char HEAD = '@';
    final char TAIL = '+';
    final char VOCAL_BONE = '#';
    final char EMPTY = '.';
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
        int minHeight = 0;
        int maxHeight = lines.size();
        int minWidth = 0;
        int maxWidth = lines.getFirst().length();
        Map<List<Integer>, Character> grid = new HashMap<>();
        List<Integer> curPos = findChar(HEAD, lines);
        grid.put(curPos, HEAD);
        List<Integer> vocalBone = findChar(VOCAL_BONE, lines);
        grid.put(vocalBone, VOCAL_BONE);
        int curDirection = 0;
        int steps = 0;
        while (!isSurrounded(vocalBone, grid, minWidth, maxWidth, minHeight, maxHeight)) {
            List<Integer> direction = DIRECTIONS.get(curDirection);
            List<Integer> targetPos = List.of((curPos.getFirst() + direction.getFirst()), (curPos.getLast() + direction.getLast()));
            while (!attemptMoveToPosAndRespectVocalBone(targetPos, grid)) {
                curDirection = (curDirection + 1) % DIRECTIONS.size();
                direction = DIRECTIONS.get(curDirection);
                targetPos = List.of((curPos.getFirst() + direction.getFirst()), (curPos.getLast() + direction.getLast()));
            }
            curDirection = (curDirection + 1) % DIRECTIONS.size();
            grid.put(curPos, TAIL);
            curPos = targetPos;
            steps++;
            minHeight = grid.keySet().stream().map(List::getFirst).min(Integer::compareTo).get() - 1;
            maxHeight = grid.keySet().stream().map(List::getFirst).max(Integer::compareTo).get() + 2;
            minWidth = grid.keySet().stream().map(List::getLast).min(Integer::compareTo).get() - 1;
            maxWidth = grid.keySet().stream().map(List::getLast).max(Integer::compareTo).get() + 2;
            fillSurroundedAdjacentCells(grid, curPos, minWidth, maxWidth, minHeight, maxHeight);
//            printGrid(grid, minHeight, maxHeight, minWidth, maxWidth, steps);
        }
        return steps;
    }

    private void fillSurroundedAdjacentCells(Map<List<Integer>, Character> grid, List<Integer> curPos, int minWidth, int maxWidth, int minHeight, int maxHeight) {
        List<List<Integer>> adjacentCells = DIRECTIONS.stream().map(direction -> List.of(curPos.getFirst() + direction.getFirst(), curPos.getLast() + direction.getLast())).toList();
        adjacentCells.forEach(cell -> {
            List<List<Integer>> surroundedArea = getSurroundedArea(cell, grid, minWidth, maxWidth, minHeight, maxHeight);
            if (surroundedArea.isEmpty()) {
                return;
            }
            surroundedArea.forEach(surroundedCell -> grid.put(surroundedCell, TAIL));
        });
    }

    private void printGrid(Map<List<Integer>, Character> grid, int minHeight, int maxHeight, int minWidth, int maxWidth, int step) {
        System.out.println("Step: " + step);
        for (int i = minHeight; i < maxHeight; i++) {
            for (int j = minWidth; j < maxWidth; j++) {
                System.out.print(grid.getOrDefault(List.of(i, j), EMPTY));
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean isSurrounded(List<Integer> cell, Map<List<Integer>, Character> grid, int minWidth, int maxWidth,
                                 int minHeight, int maxHeight) {
        List<List<Integer>> visited = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(cell);
        while (!queue.isEmpty()) {
            List<Integer> cur = queue.poll();
            if (cur.getFirst() == minWidth || cur.getFirst() == maxWidth - 1 || cur.getLast() == minHeight || cur.getLast() == maxHeight - 1) {
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

    private List<List<Integer>> getSurroundedArea(List<Integer> cell, Map<List<Integer>, Character> grid, int minWidth, int maxWidth,
                                 int minHeight, int maxHeight) {
        List<List<Integer>> visited = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(cell);
        while (!queue.isEmpty()) {
            List<Integer> cur = queue.poll();
            if (grid.containsKey(cur)) {
                continue;
            }
            if (cur.getFirst() == minWidth || cur.getFirst() == maxWidth - 1 || cur.getLast() == minHeight || cur.getLast() == maxHeight - 1) {
                return List.of();
            }
            if (visited.contains(cur)) {
                continue;
            }
            visited.add(cur);
            for (List<Integer> direction : DIRECTIONS) {
                queue.add(List.of(cur.getFirst() + direction.getFirst(), cur.getLast() + direction.getLast()));
            }
        }
        return visited;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
