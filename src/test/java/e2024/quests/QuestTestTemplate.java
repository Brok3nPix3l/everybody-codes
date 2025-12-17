package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QuestTestTemplate {
    static QuestLong questLong;

    @BeforeAll
    public static void setUpClass() {
//        questLong = new Quest();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(0, questLong.part1("""
                """));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(0, questLong.part2("""
                """));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(0, questLong.part3("""
                """));
    }
}
