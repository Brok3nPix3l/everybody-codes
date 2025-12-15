package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QuestTestTemplate {
    static Quest quest;

    @BeforeAll
    public static void setUpClass() {
//        quest = new Quest();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(0, quest.part1("""
                """));
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
