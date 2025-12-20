package e2024.quests;

import utils.StringUtils;

import java.util.List;

public class Quest8 extends QuestLong {
    // Place one block at the location where the center of the structure will be,
    // ensuring the shrine always has a single highest point.
    // Determine the thickness of the subsequent layer. For a pyramid, the thickness of all layers will always be 1.
    // Begin adding the new layer by placing blocks on the ground to the right and left of the structure. Add new blocks to all columns that existed in the previous layer.
    // Continue adding subsequent layers following the same principles.

    // Build as much of a pyramid as possible with the given blocks (input), determine how many blocks are missing, and then determine the thickness of the resulting pyramid

    // term 0 is 1, with a width of 1
    // term 1 is 4, with a width of 3
    // term 2 is 9, with a width of 5
    // term 3 is 16, with a width of 7
    // term 4 is 25, with a width of 9

    // start with width of 1 and 1 block used
    // while we still have more blocks, increase width by 2 and add one block for each column
    // once we've used more blocks than we started with, determine the difference and multiply by the width
    @Override
    public long part1(String input) {
        int blocks = Integer.parseInt(input);
        int totalBlocksUsed = 1;
        int width = 1;
        while (totalBlocksUsed < blocks) {
            width += 2;
            totalBlocksUsed += width;
        }
        int blocksRequired = totalBlocksUsed - blocks;
        return (long) blocksRequired * width;
    }

    @Override
    public long part2(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }
}
