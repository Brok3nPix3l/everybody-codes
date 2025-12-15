package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest3Test {
    static Quest quest;

    @BeforeAll
    public static void setUpClass() {
        quest = new Quest3();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(35, quest.part1("..........\n" + "..###.##..\n" + "...####...\n" + "..######..\n" +
                "..######..\n" + "...####...\n" + ".........."));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(0, quest.part2(""));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(0, quest.part3(""));
    }
}
