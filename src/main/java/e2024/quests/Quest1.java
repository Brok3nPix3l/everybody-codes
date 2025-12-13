package main.java.e2024.quests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Quest1 extends Quest {
    public Quest1(File notes) throws FileNotFoundException {
        super(notes);
    }

    @Override
    public long Part1(boolean debug) throws FileNotFoundException {
        long ans = 0L;
        try (Scanner scanner = new Scanner(notes)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (debug) {
                    System.out.println(line);
                }
            }
        }
        return ans;
    }

    @Override
    public long Part2(boolean debug) throws FileNotFoundException {
        throw new RuntimeException("Not implemented yet");
//        long ans = 0L;
//        try (Scanner scanner = new Scanner(notes)) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if (debug) {
//                    System.out.println(line);
//                }
//            }
//        }
//        return ans;
    }

    @Override
    public long Part3(boolean debug) throws FileNotFoundException {
        throw new RuntimeException("Not implemented yet");
//        long ans = 0L;
//        try (Scanner scanner = new Scanner(notes)) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if (debug) {
//                    System.out.println(line);
//                }
//            }
//        }
//        return ans;
    }
}
