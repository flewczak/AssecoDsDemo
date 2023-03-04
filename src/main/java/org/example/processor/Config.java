package org.example.processor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Config {
    private final int numberOfSpaces;
    private final int increment;

    public String getLineBeginning() {
        return " ".repeat(numberOfSpaces);
    }

    public Config getNextLevelConfig() {
        int nextNumberOfSpaces = numberOfSpaces + increment;
        int nextIncr = Math.max(1, increment - 1);
        return new Config(nextNumberOfSpaces, nextIncr);
    }
}
