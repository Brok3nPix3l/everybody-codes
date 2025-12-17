package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest6Test {
    static Quest6 quest6;

    @BeforeAll
    public static void setUpClass() {
        quest6 = new Quest6();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals("RRB@", quest6.part1("""
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
        Assertions.assertEquals(0, quest6.part2("""
                """));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(0, quest6.part3("""
                """));
    }
}
