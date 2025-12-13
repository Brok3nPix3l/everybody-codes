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
        String words = "WORDS:THE,OWE,MES,ROD,HER,QAQ";
        Assertions.assertEquals(15, quest.part2(words + "\n\n" + "AWAKEN THE POWER ADORNED WITH THE FLAMES BRIGHT IRE"));
        Assertions.assertEquals(9, quest.part2(words + "\n\n" + "THE FLAME SHIELDED THE HEART OF THE KINGS"));
        Assertions.assertEquals(6, quest.part2(words + "\n\n" + "POWE PO WER P OWE R"));
        Assertions.assertEquals(7, quest.part2(words + "\n\n" + "THERE IS THE END"));
        Assertions.assertEquals(5, quest.part2(words + "\n\n" + "QAQAQ"));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(10, quest.part3(
                "WORDS:THE,OWE,MES,ROD,RODEO\n" +
                "\n" +
                "HELWORLT\n" +
                "ENIGWDXL\n" +
                "TRODEOAL"));
    }
}
