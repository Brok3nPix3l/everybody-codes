package e2024.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Quest2Test {
    static QuestLong questLong;

    @BeforeAll
    public static void setUpClass() {
        questLong = new Quest2();
    }

    @Test
    void part1Test() {
        String words = "WORDS:THE,OWE,MES,ROD,HER";
        Assertions.assertEquals(4, questLong.part1(words + "\n\n" + "AWAKEN THE POWER ADORNED WITH THE FLAMES BRIGHT IRE"));
        Assertions.assertEquals(3, questLong.part1(words + "\n\n" + "THE FLAME SHIELDED THE HEART OF THE KINGS"));
        Assertions.assertEquals(2, questLong.part1(words + "\n\n" + "POWE PO WER P OWE R"));
        Assertions.assertEquals(3, questLong.part1(words + "\n\n" + "THERE IS THE END"));
    }

    @Test
    void part2Test() {
        String words = "WORDS:THE,OWE,MES,ROD,HER,QAQ";
        Assertions.assertEquals(15, questLong.part2(words + "\n\n" + "AWAKEN THE POWER ADORNED WITH THE FLAMES BRIGHT IRE"));
        Assertions.assertEquals(9, questLong.part2(words + "\n\n" + "THE FLAME SHIELDED THE HEART OF THE KINGS"));
        Assertions.assertEquals(6, questLong.part2(words + "\n\n" + "POWE PO WER P OWE R"));
        Assertions.assertEquals(7, questLong.part2(words + "\n\n" + "THERE IS THE END"));
        Assertions.assertEquals(5, questLong.part2(words + "\n\n" + "QAQAQ"));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(10, questLong.part3(
                "WORDS:THE,OWE,MES,ROD,RODEO\n" +
                "\n" +
                "HELWORLT\n" +
                "ENIGWDXL\n" +
                "TRODEOAL"));
    }
}
