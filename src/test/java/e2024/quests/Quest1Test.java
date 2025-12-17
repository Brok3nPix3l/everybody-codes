package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class Quest1Test {
    static QuestLong questLong;

    @BeforeAll
    public static void setUpClass() {
        questLong = new Quest1();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(5, questLong.part1("ABBAC"));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(28, questLong.part2("AxBCDDCAxD"));
        Assertions.assertEquals(0, questLong.part2("Ax"));
        Assertions.assertEquals(6, questLong.part2("BC"));
        Assertions.assertEquals(12, questLong.part2("DD"));
        Assertions.assertEquals(5, questLong.part2("CA"));
        Assertions.assertEquals(5, questLong.part2("xD"));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(30, questLong.part3("xBxAAABCDxCC"));
    }
}
