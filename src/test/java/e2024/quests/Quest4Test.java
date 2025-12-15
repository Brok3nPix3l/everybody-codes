package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest4Test {
    static Quest quest;

    @BeforeAll
    public static void setUpClass() {
        quest = new Quest4();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(10, quest.part1("""
                3
                4
                7
                8"""));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(10, quest.part2("""
                3
                4
                7
                8"""));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(0, quest.part3(""));
    }
}
