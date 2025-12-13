import e2024.quests.Quest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.*;


public class Driver {
    public static void main(String[] args) throws ClassNotFoundException {
        Properties properties = parseArgs(args);
        if (!properties.containsKey("year")) {
            throw new RuntimeException("year is required");
        }
        int year = Integer.parseInt(properties.getProperty("year"));
        if (!properties.containsKey("questNumber")) {
            throw new RuntimeException("questNumber is required");
        }
        int questNumber = Integer.parseInt(properties.getProperty("questNumber"));
        if (!properties.containsKey("part")) {
            throw new RuntimeException("part is required");
        }
        int part = Integer.parseInt(properties.getProperty("part"));
        String fileSuffix = Optional.ofNullable(properties.getProperty("fileSuffix")).orElse("");
        boolean profile = Boolean.parseBoolean(properties.getProperty("profile"));
        boolean debug = Boolean.parseBoolean(properties.getProperty("debug"));

        Quest quest;
        try {
            String className = "e2024.quests.Quest" + questNumber;
            Class<?> dayClass = Class.forName(className);
            Constructor<?> constructor;
            try {
                constructor = dayClass.getConstructor();
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
            quest = (Quest) constructor.newInstance();
            Path filePath = Path.of(String.format("e%s", year), "quests",
                    String.format("everybody_codes_e%d_q%02d_p%d%s.txt", year, questNumber, part, fileSuffix));
            String input;
            try (InputStream inputstream = Driver.class.getResourceAsStream(
                    filePath.toString().replace("\\", "/"))) {
                input = new String(Objects.requireNonNull(inputstream).readAllBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (profile) {
                long startTime = System.nanoTime();
                runMethod(input, debug, quest, part);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1_000_000;
                System.out.println("Execution time: " + duration + "ms");
            } else {
                runMethod(input, debug, quest, part);
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void runMethod(String input, boolean debug, Quest quest, int part)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method partMethod = quest.getClass().getMethod("part" + part, String.class, boolean.class);
        System.out.println(partMethod.invoke(quest, input, debug));
    }

    private static Properties parseArgs(String[] args) {
        Properties properties = new Properties();
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("-") && args[i].length() > 1) {
                properties.setProperty(args[i].substring(1), args[i + 1]);
                i++;
            }
        }
        return properties;
    }
}