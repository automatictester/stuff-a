package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleClass {

    private final LocalTimer timer;
    private final Map<String, Integer> count = new HashMap<>();

    public SampleClass(LocalTimer timer) {
        this.timer = timer;
        List<String> supportedCommands = Arrays.asList("VERSION", "PING", "TIME", "COUNT");
        supportedCommands.forEach(command -> count.put(command, 0));
    }

    public String handleCommand(String commandLine) {
        List<String> commandList = Arrays.asList(commandLine.split(" "));
        int size = commandList.size();
        if (size == 0 || size > 2) {
            throw new IllegalArgumentException();
        } else {
            String command = commandList.get(0);
            String arg = (size == 2) ? commandList.get(1) : "";
            switch (command) {
                case "VERSION":
                    incrementCount(command);
                    return "1.0";
                case "TIME":
                    incrementCount(command);
                    return timer.now();
                case "COUNT":
                    incrementCount(command);
                    return count.toString();
                case "PING":
                    incrementCount(command);
                    return (size == 1) ? "pong" : arg;
                default:
                    throw new RuntimeException();
            }
        }
    }

    private void incrementCount(String key) {
        count.compute(key, (k, v) -> ++v);
    }
}
