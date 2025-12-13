package main.java.e2024.quests;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

public abstract class Quest {
    protected final File notes;
    public Quest(File notes) throws FileNotFoundException {
        this.notes = notes;
        if (!Files.exists(notes.toPath()) || !Files.isRegularFile(notes.toPath())) {
            throw new FileNotFoundException("The specified file does not exist or is not a regular file: " + notes.toPath());
        }
    }
    public abstract long Part1(boolean debug) throws FileNotFoundException;
    public abstract long Part2(boolean debug) throws FileNotFoundException;
    public abstract long Part3(boolean debug) throws FileNotFoundException;
}
