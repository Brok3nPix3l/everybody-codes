package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Quest5Test {
    static Quest quest;

    @BeforeAll
    public static void setUpClass() {
        quest = new Quest5();
    }

    @Test
    void part1Test() {
        Quest5 quest5 = new Quest5();
        assertEquals(3345, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 1));
        assertEquals(3245, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 2));
        assertEquals(3255, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 3));
        assertEquals(3252, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 4));
        assertEquals(4252, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 5));
        assertEquals(4452, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 6));
        assertEquals(4422, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 7));
        assertEquals(4423, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 8));
        assertEquals(2423, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 9));
        assertEquals(2323, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 10));
        assertEquals(2323, quest.part1("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4"""));
    }

    @Test
    void part2Test() {
        Quest5 quest5 = new Quest5();
        String input = """
                2 3 4 5
                6 7 8 9""";
        List<String> lines = StringUtils.splitInput(input);
        Map<Integer, List<Integer>> columns = quest5.generateColumns(lines);
        int clapperColumnIndex = 0;

        quest5.performRound(columns, clapperColumnIndex);
        Map<Integer,List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(0, List.of(6));
        expectedMap.put(1, List.of(3, 2, 7));
        expectedMap.put(2, List.of(4, 8));
        expectedMap.put(3, List.of(5, 9));
        assertEquals(expectedMap, columns);

        clapperColumnIndex = (clapperColumnIndex + 1) % columns.size();
        quest5.performRound(columns, clapperColumnIndex);
        expectedMap.clear();
        expectedMap.put(0, List.of(6));
        expectedMap.put(1, List.of(2, 7));
        expectedMap.put(2, List.of(4, 8, 3));
        expectedMap.put(3, List.of(5, 9));
        assertEquals(expectedMap, columns);

        clapperColumnIndex = (clapperColumnIndex + 1) % columns.size();
        quest5.performRound(columns, clapperColumnIndex);
        expectedMap.clear();
        expectedMap.put(0, List.of(6));
        expectedMap.put(1, List.of(2, 7));
        expectedMap.put(2, List.of(8, 3));
        expectedMap.put(3, List.of(5, 4, 9));
        assertEquals(expectedMap, columns);

        clapperColumnIndex = (clapperColumnIndex + 1) % columns.size();
        quest5.performRound(columns, clapperColumnIndex);
        expectedMap.clear();
        expectedMap.put(0, List.of(5, 6));
        expectedMap.put(1, List.of(2, 7));
        expectedMap.put(2, List.of(8, 3));
        expectedMap.put(3, List.of(4, 9));
        assertEquals(expectedMap, columns);

        clapperColumnIndex = (clapperColumnIndex + 1) % columns.size();
        quest5.performRound(columns, clapperColumnIndex);
        expectedMap.clear();
        expectedMap.put(0, List.of(6));
        expectedMap.put(1, List.of(5, 2, 7));
        expectedMap.put(2, List.of(8, 3));
        expectedMap.put(3, List.of(4, 9));
        assertEquals(expectedMap, columns);
//        Assertions.assertEquals(6345, quest5.performRounds("""
//                2 3 4 5
//                6 7 8 9""", 1));
//        Assertions.assertEquals(6245, quest5.performRounds("""
//                2 3 4 5
//                6 7 8 9""", 2));
//        Assertions.assertEquals(6285, quest5.performRounds("""
//                2 3 4 5
//                6 7 8 9""", 3));
//        Assertions.assertEquals(5284, quest5.performRounds("""
//                2 3 4 5
//                6 7 8 9""", 4));
        assertEquals(6584, quest5.performRounds("""
                2 3 4 5
                6 7 8 9""", 5));
        assertEquals(6254, quest5.performRounds("""
                2 3 4 5
                6 7 8 9""", 6));
        assertEquals(6285, quest5.performRounds("""
                2 3 4 5
                6 7 8 9""", 7));
        assertEquals(5284, quest5.performRounds("""
                2 3 4 5
                6 7 8 9""", 8));
        assertEquals(6584, quest5.performRounds("""
                2 3 4 5
                6 7 8 9""", 9));
        assertEquals(6254, quest5.performRounds("""
                2 3 4 5
                6 7 8 9""", 10));
        assertEquals(50877075, quest.part2("""
                2 3 4 5
                6 7 8 9"""));
    }

    @Test
    void part3Test() {
        assertEquals(0, quest.part3("""
                """));
    }
}
