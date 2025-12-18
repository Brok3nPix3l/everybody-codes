package e2024.quests;

import utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Quest7 extends QuestString {
    final char START = 'S';
    final char EMPTY = ' ';

    private static void extractTrack(String line, List<String> track, List<String> leftSideOfTrack) {
        if (!line.contains(" ")) {
            if (track.isEmpty()) {
                // at the start of the track
                track.addAll(Arrays.stream(line.split("")).toList());
            } else {
                // at the end of the track
                track.addAll(Arrays.stream(line.split("")).toList().reversed());
            }
        } else {
            // in the middle of the track
            track.add(line.substring(line.length() - 1));
            leftSideOfTrack.add(line.substring(0, 1));
        }
    }

    private static void extractActionPlan(String line, Map<String, List<String>> actionPlans) {
        String[] parts = line.split(":");
        String actionPlanName = parts[0];
        List<String> actions = Arrays.stream(parts[1].split(",")).toList();
        actionPlans.put(actionPlanName, actions);
    }

    @Override
    public String part1(String input) {
        List<String> lines = StringUtils.splitInput(input);
        Map<String, Integer> actionPlanTotalEssenceGathered = new HashMap<>();
        List<String> actionPlans = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(":");
            String actionPlanName = parts[0];
            actionPlans.add(actionPlanName);
            String[] actions = parts[1].split(",");
            int totalEssenceGathered = 0;
            int currentPower = 10;
            int currentAction = 0;
            for (int i = 0; i < 10; i++) {
                switch (actions[currentAction]) {
                    case "+":
                        currentPower++;
                        break;
                    case "-":
                        currentPower = Math.max(currentPower - 1, 0);
                        break;
                }
                totalEssenceGathered += currentPower;
                currentAction = (currentAction + 1) % actions.length;
            }
            actionPlanTotalEssenceGathered.put(actionPlanName, totalEssenceGathered);
        }
        actionPlans.sort(Comparator.<String>comparingInt(actionPlanTotalEssenceGathered::get).reversed());
        return String.join("", actionPlans);
    }

    @Override
    public String part2(String input) {
        List<String> lines = StringUtils.splitInput(input);
        Map<String, Integer> actionPlanTotalEssenceGathered = new HashMap<>();
        Map<String, List<String>> actionPlans = new HashMap<>();
        List<String> track = new ArrayList<>();
        List<String> leftSideOfTrack = new ArrayList<>();
        boolean readingActionPlans = true;
        for (String line : lines) {
            if (line.isEmpty()) {
                readingActionPlans = false;
                continue;
            }
            if (readingActionPlans)
                extractActionPlan(line, actionPlans);
            else
                extractTrack(line, track, leftSideOfTrack);
        }
        track.addAll(leftSideOfTrack);
        for (Map.Entry<String, List<String>> actionPlan : actionPlans.entrySet()) {
            int totalEssenceGathered = 0;
            int currentPower = 10;
            int currentAction = 0;
            int currentLapCount = 0;
            int currentTrackIndex = 1;
            List<String> actions = actionPlan.getValue();
            while (currentLapCount < 10) {
                if (track.get(currentTrackIndex).equals(START)) {
                    currentLapCount++;
                }
                switch (track.get(currentTrackIndex)) {
                    case "+":
                        currentPower++;
                        break;
                    case "-":
                        currentPower = Math.max(currentPower - 1, 0);
                        break;
                    default:
                        switch (actions.get(currentAction)) {
                            case "+":
                                currentPower++;
                                break;
                            case "-":
                                currentPower = Math.max(currentPower - 1, 0);
                                break;
                        }
                }
                totalEssenceGathered += currentPower;
                currentAction = (currentAction + 1) % actions.size();
                currentTrackIndex = (currentTrackIndex + 1) % track.size();
            }
            actionPlanTotalEssenceGathered.put(actionPlan.getKey(), totalEssenceGathered);
        }
        List<String> actionPlanNames = actionPlanTotalEssenceGathered.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).toList().reversed();
        return String.join("", actionPlanNames);
    }

    @Override
    public String part3(String input) {
        List<String> lines = StringUtils.splitInput(input);
        Map<String, Integer> actionPlanTotalEssenceGathered = new HashMap<>();
        Map<String, List<String>> actionPlans = generateActionPlans();
        List<String> opponentActionPlanActions;
        String opponentActionPlanName = "";
        List<String> track = new ArrayList<>();
        boolean readingActionPlans = true;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank()) {
                readingActionPlans = false;
                continue;
            }
            if (readingActionPlans) {
                opponentActionPlanActions = extractActionPlan(line);
                List<String> finalOpponentActionPlanActions = opponentActionPlanActions;
                opponentActionPlanName = actionPlans.entrySet().stream()
                        .filter(e -> e.getValue().equals(finalOpponentActionPlanActions)).findFirst()
                        .orElseThrow().getKey();
            } else {
                track = extractTrack(lines.subList(i, lines.size()));
                break;
            }
        }
        for (Map.Entry<String, List<String>> actionPlan : actionPlans.entrySet()) {
            int totalEssenceGathered = 0;
            int currentPower = 10;
            int currentAction = 0;
            int currentLapCount = 0;
            int currentTrackIndex = 1;
            List<String> actions = actionPlan.getValue();
            while (currentLapCount < 2024) {
                if (track.get(currentTrackIndex).equals(START + "")) {
                    currentLapCount++;
                }
                switch (track.get(currentTrackIndex)) {
                    case "+":
                        currentPower++;
                        break;
                    case "-":
                        currentPower = Math.max(currentPower - 1, 0);
                        break;
                    default:
                        switch (actions.get(currentAction)) {
                            case "+":
                                currentPower++;
                                break;
                            case "-":
                                currentPower = Math.max(currentPower - 1, 0);
                                break;
                        }
                }
                totalEssenceGathered += currentPower;
                currentAction = (currentAction + 1) % actions.size();
                currentTrackIndex = (currentTrackIndex + 1) % track.size();
            }
            actionPlanTotalEssenceGathered.put(actionPlan.getKey(), totalEssenceGathered);
            if (actionPlanTotalEssenceGathered.size() % 100 == 0) {
                System.out.println(actionPlanTotalEssenceGathered.size() + "/" + actionPlans.size());
            }
        }
        int opponentTotalEssenceGathered = actionPlanTotalEssenceGathered.get(opponentActionPlanName);
        long winningActionPlanCount =
                actionPlanTotalEssenceGathered.values().stream().filter(i -> i > opponentTotalEssenceGathered)
                        .count();
        return String.valueOf(winningActionPlanCount);
    }

    private List<String> extractActionPlan(String line) {
        String[] parts = line.split(":");
        return Arrays.stream(parts[1].trim().split(",")).toList();
    }

    // Each knight must make their action plan, making sure it is precisely 11 actions long, consisting of:
    // 5 actions of  +
    // 3 actions of  -
    // and the remaining 3 as  =
    private Map<String, List<String>> generateActionPlans() {
        int actionPlanNumber = 0;
        Map<String, List<String>> actionPlans = new HashMap<>();
        List<List<String>> actionLists = addActionToPlan("+", new ArrayList<>(), 4, 3, 3);
        for (List<String> actionList : actionLists) {
            actionPlans.put(String.valueOf(actionPlanNumber++), actionList);
        }
        actionLists = addActionToPlan("-", new ArrayList<>(), 5, 2, 3);
        for (List<String> actionList : actionLists) {
            actionPlans.put(String.valueOf(actionPlanNumber++), actionList);
        }
        actionLists = addActionToPlan("=", new ArrayList<>(), 5, 3, 2);
        for (List<String> actionList : actionLists) {
            actionPlans.put(String.valueOf(actionPlanNumber++), actionList);
        }
        return actionPlans;
    }

    private List<List<String>> addActionToPlan(String currentActionToAdd, ArrayList<String> actions,
                                               int plusCount, int minusCount, int neutralCount) {
        actions.add(currentActionToAdd);
        if (plusCount == 0 && minusCount == 0 && neutralCount == 0) {
            return List.of(actions);
        }

        List<List<String>> actionLists = new ArrayList<>();
        if (plusCount > 0) {
            actionLists.addAll(
                    addActionToPlan("+", new ArrayList<>(actions), plusCount - 1, minusCount, neutralCount));
        }
        if (minusCount > 0) {
            actionLists.addAll(
                    addActionToPlan("-", new ArrayList<>(actions), plusCount, minusCount - 1, neutralCount));
        }
        if (neutralCount > 0) {
            actionLists.addAll(
                    addActionToPlan("=", new ArrayList<>(actions), plusCount, minusCount, neutralCount - 1));
        }
        return actionLists;
    }

    // example:
    // S+= +=-== +=++=     =+=+=--=    =-= ++=     +=-  =+=++=-+==+ =++=-=-=--
    // - + +   + =   =     =      =   == = - -     - =  =         =-=        -
    // = + + +-- =-= ==-==-= --++ +  == == = +     - =  =    ==++=    =++=-=++
    // + + + =     +         =  + + == == ++ =     = =  ==   =   = =++=
    // = = + + +== +==     =++ == =+=  =  +  +==-=++ =   =++ --= + =
    // + ==- = + =   = =+= =   =       ++--          +     =   = = =--= ==++==
    // =     ==- ==+-- = = = ++= +=--      ==+ ==--= +--+=-= ==- ==   =+=    =
    // -               = = = =   +  +  ==+ = = +   =        ++    =          -
    // -               = + + =   +  -  = + = = +   =        +     =          -
    // --==++++==+=+++-= =-= =-+-=  =+-= =-= =--   +=++=+++==     -=+=++==+++-
    private List<String> extractTrack(List<String> lines) {
        int[] currentPosition = new int[]{0, 1};
        int[] previousPosition = new int[]{0, 0};
        List<String> flatTrack = new ArrayList<>();
        flatTrack.add(START + "");
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Set<List<Integer>> visited = new HashSet<>();
        while (lines.get(currentPosition[0]).charAt(currentPosition[1]) != START) {
            flatTrack.add(lines.get(currentPosition[0]).charAt(currentPosition[1]) + "");
            for (int[] direction : directions) {
                int[] newPosition =
                        new int[]{currentPosition[0] + direction[0], currentPosition[1] + direction[1]};
                if (newPosition[0] >= 0 && newPosition[0] < lines.size() && newPosition[1] >= 0 &&
                        newPosition[1] < lines.get(newPosition[0]).length() &&
                        lines.get(newPosition[0]).charAt(newPosition[1]) != EMPTY &&
                        !Arrays.equals(newPosition, previousPosition)) {
                    previousPosition[0] = currentPosition[0];
                    previousPosition[1] = currentPosition[1];
                    currentPosition = newPosition;
                    visited.add(List.of(currentPosition[0], currentPosition[1]));
                    break;
                }
            }
//            if (currentPosition[0] > 0) {
//                int[] newPosition = new int[]{currentPosition[0] - 1, currentPosition[1]};
//                if (lines.get(newPosition[0]).charAt(newPosition[1]) != EMPTY &&
//                        !Arrays.equals(newPosition, previousPosition)) {
//                    previousPosition[0] = currentPosition[0];
//                    previousPosition[1] = currentPosition[1];
//                    currentPosition[0]--;
//                    continue;
//                }
//            }
//            if (currentPosition[0] < lines.size() - 1) {
//                int[] newPosition = new int[]{currentPosition[0] + 1, currentPosition[1]};
//                if (lines.get(newPosition[0]).charAt(newPosition[1]) != EMPTY && !Arrays.equals(newPosition,
//                previousPosition)) {
//                    previousPosition[0] = currentPosition[0];
//                    previousPosition[1] = currentPosition[1];
//                    currentPosition[0]++;
//                    continue;
//                }
//            }
//            if (currentPosition[1] > 0) {
//                int[] newPosition = new int[]{currentPosition[0], currentPosition[1] - 1};
//                if (lines.get(newPosition[0]).charAt(newPosition[1]) != EMPTY && !Arrays.equals(newPosition,
//                previousPosition)) {
//                    previousPosition[0] = currentPosition[0];
//                    previousPosition[1] = currentPosition[1];
//                    currentPosition[1]--;
//                    continue;
//                }
//            }
//            if (currentPosition[1] < lines.getFirst().length() - 1) {
//                int[] newPosition = new int[]{currentPosition[0], currentPosition[1] + 1};
//                if (lines.get(newPosition[0]).charAt(newPosition[1]) != EMPTY && !Arrays.equals(newPosition,
//                previousPosition)) {
//                    previousPosition[0] = currentPosition[0];
//                    previousPosition[1] = currentPosition[1];
//                    currentPosition[1]++;
//                    continue;
//                }
//            }
        }
        return flatTrack;
    }
}
