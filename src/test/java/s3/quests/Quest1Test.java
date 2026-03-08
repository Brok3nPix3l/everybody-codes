package s3.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest1Test {
    static QuestLong questLong;

    @BeforeAll
    public static void setUpClass() {
        questLong = new Quest1();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(9166, questLong.part1("""
                2456:rrrrrr ggGgGG bbbbBB
                7689:rrRrrr ggGggg bbbBBB
                3145:rrRrRr gggGgg bbbbBB
                6710:rrrRRr ggGGGg bbBBbB
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
