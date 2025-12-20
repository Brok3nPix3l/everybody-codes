package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest8Test {
    static QuestLong questLong;

    @BeforeAll
    public static void setUpClass() {
        questLong = new Quest8();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(21, questLong.part1("""
                13"""));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(27, questLong.part2("""
                3
                5
                50"""));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(0, questLong.part3("""
                """));
    }
}
