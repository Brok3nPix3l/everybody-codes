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
        actionPlans.sort(Comparator.comparingInt(actionPlanTotalEssenceGathered::get).reversed());
        return String.join("", actionPlans);
    }

    @Override
    public String part2(String input) {
        List<String> lines = StringUtils.splitInput(input);
        return "";
    }

    @Override
    public String part3(String input) {
        List<String> lines = StringUtils.splitInput(input);
        return "";
    }
}
