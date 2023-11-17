package com.example.adventofcode.day7.util;

import com.example.adventofcode.day7.domain.TerminalCommand;
import com.example.adventofcode.day7.domain.TerminalCommandType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class Day7InputProvider {
    @Value("${classpath:/input/day7/input.txt}")
    private Resource inputResource;

    public List<TerminalCommand> getInput() {
        String inputString = readInputToString();

        return Arrays.stream(inputString
                .split(Pattern.quote("$")))
                .map(String::trim)
                .filter(i -> !i.isEmpty())
                .map(this::parseTerminalCommand)
                .collect(Collectors.toList());
    }

    private TerminalCommand parseTerminalCommand(String commandString) {
        List<String> commands = Arrays.stream(commandString.split("\\W")).map(String::trim).filter(i -> !i.isEmpty()).toList();

        TerminalCommandType type = TerminalCommandType.getByCommandString(commands.get(0));

        if(type == null) {
            throw new RuntimeException("Big problem");
        }else {
            return switch (type) {
                case LIST_CONTENTS -> getListContentsCommand(commandString);
                case CHANGE_DIR -> getChangeDirCommand(commandString);
            };
        }
    }

    private TerminalCommand getListContentsCommand(String commandString) {
        commandString = commandString.replace("ls\n", "");
        List<String> data = Arrays.stream(commandString.split("\n"))
                .filter(i -> i.length() > 0)
                .toList();

        return new TerminalCommand(TerminalCommandType.LIST_CONTENTS, data);
    }

    private TerminalCommand getChangeDirCommand(String commandString) {
        List<String> data = List.of(commandString.split(" ")[1]);

        return new TerminalCommand(TerminalCommandType.CHANGE_DIR, data);
    }

    private String readInputToString() {
        try (Reader reader = new InputStreamReader(inputResource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
