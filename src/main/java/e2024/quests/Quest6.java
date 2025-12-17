package e2024.quests;

import utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Quest6 extends QuestString {
    final static String ROOT = "RR";
    final static String FRUIT = "@";

    @Override
    public String part1(String input) {
        List<String> lines = StringUtils.splitInput(input);
        Map<String, String> reverseMap = new HashMap<>();
        Set<String> fruitSources = new HashSet<>();
        for (String line : lines) {
            String[] parts = line.split(":");
            String source = parts[0];
            String[] destinations = parts[1].split(",");
            for (String destination : destinations) {
                if (destination.equals(FRUIT)) {
                    fruitSources.add(source);
                } else {
                    reverseMap.put(destination, source);
                }
            }
        }
        // System.out.println("reverseMap: " + reverseMap);
        // System.out.println("fruitSources: " + fruitSources);
        Map<String, String> memo = new HashMap<>();
        List<String> pathsToRoot = new ArrayList<>();
        Map<Integer, Integer> fruitPathDistanceFreqMap = new HashMap<>();
        for (String fruitSource : fruitSources) {
            String path = computePathToRootFrom(fruitSource, "", memo, reverseMap) + FRUIT;
            pathsToRoot.add(path);
            fruitPathDistanceFreqMap.put(path.length(),
                    fruitPathDistanceFreqMap.getOrDefault(path.length(), 0) + 1);
        }
        System.out.println("pathsToRoot: " + pathsToRoot);
        System.out.println("fruitPathDistanceFreqMap: " + fruitPathDistanceFreqMap);
        int targetLength =
                fruitPathDistanceFreqMap.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue))
                        .map(Map.Entry::getKey).orElse(0);
        System.out.println("targetLength: " + targetLength);
        String ans = "";
        for (String path : pathsToRoot) {
            if (path.length() == targetLength) {
                ans = path;
                break;
            }
        }
        return ans;
    }

    private String computePathToRootFrom(String currentNode, String pathToRoot, Map<String, String> memo,
                                         Map<String, String> reverseMap) {
        if (currentNode.equals(ROOT)) {
            return ROOT;
        }

        if (memo.containsKey(currentNode)) {
            return memo.get(currentNode) + pathToRoot;
        }

        String path = computePathToRootFrom(reverseMap.get(currentNode), pathToRoot, memo, reverseMap) + currentNode + pathToRoot;
        memo.put(currentNode, path);
        return path;
    }

    private List<String> computePathToRootAsListFrom(String currentNode, List<String> pathToRoot, Map<String, List<String>> memo,
                                             Map<String, String> reverseMap) {
        if (currentNode.equals(ROOT)) {
            return List.of(ROOT);
        }

        if (memo.containsKey(currentNode)) {
            return new ArrayList<>(memo.get(currentNode));
        }

        List<String> path = new ArrayList<>(pathToRoot);
        path.addFirst(currentNode);
        path.addAll(0, computePathToRootAsListFrom(reverseMap.get(currentNode), pathToRoot, memo, reverseMap));
        memo.put(currentNode, path);
        return path;
    }

    public String part2(String input) {
        List<String> lines = StringUtils.splitInput(input);
        Map<String, String> reverseMap = new HashMap<>();
        Set<String> fruitSources = new HashSet<>();
        for (String line : lines) {
            String[] parts = line.split(":");
            String source = parts[0];
            String[] destinations = parts[1].trim().split(",");
            for (String destination : destinations) {
                if (destination.equals(FRUIT)) {
                    fruitSources.add(source);
                } else {
                    reverseMap.put(destination, source);
                }
            }
        }
         System.out.println("reverseMap: " + reverseMap);
         System.out.println("fruitSources: " + fruitSources);
        Map<String, List<String>> memo = new HashMap<>();
        List<List<String>> pathsToRoot = new ArrayList<>();
        Map<Integer, Integer> fruitPathDistanceFreqMap = new HashMap<>();
        for (String fruitSource : fruitSources) {
            List<String> path = new ArrayList<>(computePathToRootAsListFrom(fruitSource, new ArrayList<>(), memo, reverseMap));
            path.add(FRUIT);
            pathsToRoot.add(path);
            fruitPathDistanceFreqMap.put(path.size(),
                    fruitPathDistanceFreqMap.getOrDefault(path.size(), 0) + 1);
        }
        System.out.println("pathsToRoot: " + pathsToRoot);
        System.out.println("fruitPathDistanceFreqMap: " + fruitPathDistanceFreqMap);
        System.out.println("memo: " + memo);
        int targetLength =
                fruitPathDistanceFreqMap.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue))
                        .map(Map.Entry::getKey).orElse(0);
        System.out.println("targetLength: " + targetLength);
        String ans = "";
        // it was decided that each tree would maintain exactly one path to a fruit with a unique length.
        // i.e. there is only one path with a unique length; all others are repeated
        for (List<String> path : pathsToRoot) {
            if (path.size() == targetLength) {
                ans = path.stream().map(e -> e.substring(0, 1)).collect(Collectors.joining());
                break;
            }
        }
        return ans;
    }

    public String part3(String input) {
        List<String> lines = StringUtils.splitInput(input);
        return "";
    }
}
