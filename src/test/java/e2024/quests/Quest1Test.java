package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class Quest1Test {
    static Quest quest;

    @BeforeAll
    public static void setUpClass() {
        quest = new Quest1();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(5, quest.part1("ABBAC"));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(28, quest.part2("AxBCDDCAxD"));
        Assertions.assertEquals(0, quest.part2("Ax"));
        Assertions.assertEquals(6, quest.part2("BC"));
        Assertions.assertEquals(12, quest.part2("DD"));
        Assertions.assertEquals(5, quest.part2("CA"));
        Assertions.assertEquals(5, quest.part2("xD"));
    }
}
