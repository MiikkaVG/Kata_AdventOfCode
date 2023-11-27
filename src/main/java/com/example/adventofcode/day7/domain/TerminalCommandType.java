package com.example.adventofcode.day7.domain;

public enum TerminalCommandType {
    CHANGE_DIR("cd"), LIST_CONTENTS("ls");

    private final String commandString;

    TerminalCommandType(String commandString) {
        this.commandString = commandString;
    }

    public static TerminalCommandType getByCommandString(String commandString) {
        for (TerminalCommandType commandType : TerminalCommandType.values()) {
            if (commandType.commandString.equals(commandString)) {
                return commandType;
            }
        }

        return null;
    }
}
