package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest5Test {
    static Quest quest;

    @BeforeAll
    public static void setUpClass() {
        quest = new Quest5();
    }

    @Test
    void part1Test() {
        Quest5 quest5 = new Quest5();
        Assertions.assertEquals(3345, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 1));
        Assertions.assertEquals(3245, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 2));
        Assertions.assertEquals(3255, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 3));
        Assertions.assertEquals(3252, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 4));
        Assertions.assertEquals(4252, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 5));
        Assertions.assertEquals(4452, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 6));
        Assertions.assertEquals(4422, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 7));
        Assertions.assertEquals(4423, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 8));
        Assertions.assertEquals(2423, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 9));
        Assertions.assertEquals(2323, quest5.performRounds("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4""", 10));
        Assertions.assertEquals(2323, quest.part1("""
                2 3 4 5
                3 4 5 2
                4 5 2 3
                5 2 3 4"""));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(0, quest.part2("""
                """));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(0, quest.part3("""
                """));
    }
}
