package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.StringUtils;

import java.util.List;

class Quest7Test {
    static QuestString questString;

    @BeforeAll
    public static void setUpClass() {
        questString = new Quest7();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals("BDCA", questString.part1("""
                A:+,-,=,=
                B:+,=,-,+
                C:=,-,+,+
                D:=,=,=,+
                """));
    }

    @Test
    void part2Test() {
        Assertions.assertEquals("DCBA", questString.part2(StringUtils.concatenateInputs(List.of("""
                A:+,-,=,=
                B:+,=,-,+
                C:=,-,+,+
                D:=,=,=,+""",
                """
                S+===
                -   +
                =+=-+"""))));
    }

//    @Test
//    void part3Test() {
//        Assertions.assertEquals("", questString.part3("""
//                """));
//    }
}
