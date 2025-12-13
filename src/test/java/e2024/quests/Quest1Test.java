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
        Assertions.assertEquals(5, quest.part1("abc", false));
    }
}
