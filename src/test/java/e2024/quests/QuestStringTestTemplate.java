package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QuestStringTestTemplate {
    static QuestString questString;

    @BeforeAll
    public static void setUpClass() {
//        questString = new Quest();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals("", questString.part1("""
                """));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals("", questString.part2("""
                """));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals("", questString.part3("""
                """));
    }
}
