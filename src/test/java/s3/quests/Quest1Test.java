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
        Assertions.assertEquals(2456, questLong.part2("""
                2456:rrrrrr ggGgGG bbbbBB sSsSsS
                7689:rrRrrr ggGggg bbbBBB ssSSss
                3145:rrRrRr gggGgg bbbbBB sSsSsS
                6710:rrrRRr ggGGGg bbBBbB ssSSss
                """));
    }

    @Test
    void part3Test() {
        Assertions.assertEquals(292320, questLong.part3("""
                15437:rRrrRR gGGGGG BBBBBB sSSSSS
                94682:RrRrrR gGGggG bBBBBB ssSSSs
                56513:RRRrrr ggGGgG bbbBbb ssSsSS
                76346:rRRrrR GGgggg bbbBBB ssssSs
                87569:rrRRrR gGGGGg BbbbbB SssSss
                44191:rrrrrr gGgGGG bBBbbB sSssSS
                49176:rRRrRr GggggG BbBbbb sSSssS
                85071:RRrrrr GgGGgg BBbbbb SSsSss
                44303:rRRrrR gGggGg bBbBBB SsSSSs
                94978:rrRrRR ggGggG BBbBBb SSSSSS
                26325:rrRRrr gGGGgg BBbBbb SssssS
                43463:rrrrRR gGgGgg bBBbBB sSssSs
                15059:RRrrrR GGgggG bbBBbb sSSsSS
                85004:RRRrrR GgGgGG bbbBBB sSssss
                56121:RRrRrr gGgGgg BbbbBB sSsSSs
                80219:rRRrRR GGGggg BBbbbb SssSSs
                """));
    }
}
