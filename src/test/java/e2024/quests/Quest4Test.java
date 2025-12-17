package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest4Test {
    static QuestLong questLong;

    @BeforeAll
    public static void setUpClass() {
        questLong = new Quest4();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(10, questLong.part1("""
                3
                4
                7
                8"""));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(10, questLong.part2("""
                3
                4
                7
                8"""));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(8, questLong.part3("""
                2
                4
                5
                6
                8"""));
    }
}
