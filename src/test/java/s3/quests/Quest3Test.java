package s3.quests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

class Quest3Test {
    static QuestLong questLong;

    @BeforeAll
    public static void setUpClass() {
        questLong = new Quest3();
    }

    @Test
    void part1Test() {
        Assertions.assertEquals(43, questLong.part1("""
                id=1, plug=BLUE HEXAGON, leftSocket=GREEN CIRCLE, rightSocket=BLUE PENTAGON, data=?
                id=2, plug=GREEN CIRCLE, leftSocket=BLUE HEXAGON, rightSocket=BLUE CIRCLE, data=?
                id=3, plug=BLUE PENTAGON, leftSocket=BLUE CIRCLE, rightSocket=BLUE CIRCLE, data=?
                id=4, plug=BLUE CIRCLE, leftSocket=RED HEXAGON, rightSocket=BLUE HEXAGON, data=?
                id=5, plug=RED HEXAGON, leftSocket=GREEN CIRCLE, rightSocket=RED HEXAGON, data=?
                """));
        try {
            Assertions.assertEquals(5778, questLong.part1(new String(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(Path.of("s3", "quests", "everybody_codes_e3_q03_p1.txt").toString())).readAllBytes(),
                    StandardCharsets.UTF_8
            )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void part2Test() {
        Assertions.assertEquals(50, questLong.part2("""
                id=1, plug=RED TRIANGLE, leftSocket=RED TRIANGLE, rightSocket=RED TRIANGLE, data=?
                id=2, plug=GREEN TRIANGLE, leftSocket=BLUE CIRCLE, rightSocket=GREEN CIRCLE, data=?
                id=3, plug=BLUE PENTAGON, leftSocket=BLUE CIRCLE, rightSocket=GREEN CIRCLE, data=?
                id=4, plug=RED TRIANGLE, leftSocket=BLUE PENTAGON, rightSocket=GREEN PENTAGON, data=?
                id=5, plug=RED PENTAGON, leftSocket=GREEN CIRCLE, rightSocket=GREEN CIRCLE, data=?
                """));
        try {
            Assertions.assertEquals(319568, questLong.part2(new String(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(Path.of("s3", "quests", "everybody_codes_e3_q03_p2.txt").toString())).readAllBytes(),
                    StandardCharsets.UTF_8
            )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void part3Test() {
        Assertions.assertEquals(38, questLong.part3("""
                id=1, plug=RED TRIANGLE, leftSocket=RED TRIANGLE, rightSocket=RED TRIANGLE, data=?
                id=2, plug=GREEN TRIANGLE, leftSocket=BLUE CIRCLE, rightSocket=GREEN CIRCLE, data=?
                id=3, plug=BLUE PENTAGON, leftSocket=BLUE CIRCLE, rightSocket=GREEN CIRCLE, data=?
                id=4, plug=RED TRIANGLE, leftSocket=BLUE PENTAGON, rightSocket=GREEN PENTAGON, data=?
                id=5, plug=RED PENTAGON, leftSocket=GREEN CIRCLE, rightSocket=GREEN CIRCLE, data=?
                """));
        Assertions.assertEquals(60, questLong.part3("""
                id=1, plug=RED TRIANGLE, leftSocket=BLUE TRIANGLE, rightSocket=GREEN TRIANGLE, data=?
                id=2, plug=GREEN TRIANGLE, leftSocket=BLUE CIRCLE, rightSocket=GREEN CIRCLE, data=?
                id=3, plug=BLUE PENTAGON, leftSocket=BLUE CIRCLE, rightSocket=GREEN CIRCLE, data=?
                id=4, plug=RED TRIANGLE, leftSocket=BLUE PENTAGON, rightSocket=GREEN PENTAGON, data=?
                id=5, plug=BLUE TRIANGLE, leftSocket=GREEN CIRCLE, rightSocket=RED CIRCLE, data=?
                id=6, plug=BLUE TRIANGLE, leftSocket=GREEN CIRCLE, rightSocket=RED CIRCLE, data=?
                """));
        try {
            Assertions.assertEquals(388464, questLong.part3(new String(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(Path.of("s3", "quests", "everybody_codes_e3_q03_p3.txt").toString())).readAllBytes(),
                    StandardCharsets.UTF_8
            )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
