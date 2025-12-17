package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest6Test {
    static QuestString questString;

    @BeforeAll
    public static void setUpClass() {
        questString = new Quest6();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals("RRB@", questString.part1("""
                RR:A,B,C
                A:D,E
                B:F,@
                C:G,H
                D:@
                E:@
                F:@
                G:@
                H:@
                """));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals("RB@", questString.part2("""
                RR:A,B,C
                A:D,E
                B:F,@
                C:G,H
                D:@
                E:@
                F:@
                G:@
                H:@
                """));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals("", questString.part3("""
                """));
    }
}
