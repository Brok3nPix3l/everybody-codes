package e2024.quests;

import utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Quest7 extends QuestString {

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
        final String START = "S";
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
            if (readingActionPlans) extractActionPlan(line, actionPlans);
            else extractTrack(line, track, leftSideOfTrack);
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
        List<String> actionPlanNames = actionPlanTotalEssenceGathered.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).toList().reversed();
        return String.join("", actionPlanNames);
    }

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
    public String part3(String input) {
        final String START = "S";
        List<String> lines = StringUtils.splitInput(input);
        Map<String, Integer> actionPlanTotalEssenceGathered = new HashMap<>();
        Map<String, List<String>> actionPlans = generateActionPlans();
        List<String> track = extractTrack(lines);
        for (Map.Entry<String, List<String>> actionPlan : actionPlans.entrySet()) {
            int totalEssenceGathered = 0;
            int currentPower = 10;
            int currentAction = 0;
            int currentLapCount = 0;
            int currentTrackIndex = 1;
            List<String> actions = actionPlan.getValue();
            while (currentLapCount < 2024) {
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
        int maxEssenceGathered = actionPlanTotalEssenceGathered.values().stream().max(Comparator.comparingInt(i -> i)).orElse(-1);
        long winningActionPlanCount = actionPlanTotalEssenceGathered.values().stream().filter(i -> i == maxEssenceGathered).count();
        return String.valueOf(winningActionPlanCount);
    }

    // Each knight must make their action plan, making sure it is precisely 11 actions long, consisting of:
    // 5 actions of  +
    // 3 actions of  -
    // and the remaining 3 as  =
    private Map<String, List<String>> generateActionPlans() {

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
    private List<String> extractTrack(List<String> strings) {

    }
}
