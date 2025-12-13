package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest2Test {
    static Quest quest;

    @BeforeAll
    public static void setUpClass() {
        quest = new Quest2();
    }

    @Test
    void part1Test() {
        String words = "WORDS:THE,OWE,MES,ROD,HER";
        Assertions.assertEquals(4, quest.part1(words + "\n\n" + "AWAKEN THE POWER ADORNED WITH THE FLAMES BRIGHT IRE"));
        Assertions.assertEquals(3, quest.part1(words + "\n\n" + "THE FLAME SHIELDED THE HEART OF THE KINGS"));
        Assertions.assertEquals(2, quest.part1(words + "\n\n" + "POWE PO WER P OWE R"));
        Assertions.assertEquals(3, quest.part1(words + "\n\n" + "THERE IS THE END"));
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
