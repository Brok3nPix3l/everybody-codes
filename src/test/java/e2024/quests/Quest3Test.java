package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest3Test {
    static QuestLong questLong;

    @BeforeAll
    public static void setUpClass() {
        questLong = new Quest3();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(35, questLong.part1("..........\n" + "..###.##..\n" + "...####...\n" + "..######..\n" +
                "..######..\n" + "...####...\n" + ".........."));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(35, questLong.part2("..........\n" + "..###.##..\n" + "...####...\n" + "..######..\n" +
                "..######..\n" + "...####...\n" + ".........."));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(29, questLong.part3("..........\n" + "..###.##..\n" + "...####...\n" +
                "..######..\n" + "..######..\n" + "...####...\n" + ".........."));
    }
}
