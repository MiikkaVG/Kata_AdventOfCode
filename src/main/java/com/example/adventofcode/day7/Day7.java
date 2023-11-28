package com.example.adventofcode.day7;

import com.example.adventofcode.day7.domain.File;
import com.example.adventofcode.day7.domain.TerminalCommand;
import com.example.adventofcode.day7.util.Day7InputProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Day7 {
    @Autowired
    private Day7InputProvider inputProvider;

    private final String PARENT_DIR = "..";
    private final String DIRECTORY = "dir";
    private final Integer FILE_LIMIT = 100_000;
    private final Integer MAX_SPACE = 70_000_000;
    private final Integer REQUIRED_SPACE = 30_000_000;

    public Integer runSolution1() {
        return solution1(inputProvider.getInput());
    }

    public Integer runSolution2() {
        return solution2(inputProvider.getInput());
    }

    public Integer solution1(List<TerminalCommand> commands) {
        File upper = prepareTreeStructure(commands);

        List<File> dirList = getDirs(upper);

        return dirList.stream()
                .map(File::getFileSize)
                .filter(fileSize -> fileSize <= FILE_LIMIT)
                .reduce(0, Integer::sum);
    }

    private List<File> getDirs(File outer) {
        List<File> files = new ArrayList<>();
        files.add(outer);

        outer.getContents().stream()
                .filter(File::isDir)
                .forEach(d -> files.addAll(getDirs(d)));

        return files;
    }

    private File prepareTreeStructure(List<TerminalCommand> commands) {
        File currentDir = null;

        for (TerminalCommand command: commands) {
            currentDir = executeCommand(currentDir, command);
        }

        File upper = currentDir;

        while(upper.getParent() != null) {
            upper = upper.getParent();
        }

        addFileSizeInfo(upper);

        return upper;
    }

    private File executeCommand(File currentDir, TerminalCommand command) {
        return switch(command.getCommandType()) {
            case CHANGE_DIR -> changeDirectory(currentDir, command.getData().get(0));
            case LIST_CONTENTS -> listContents(currentDir, command.getData());
        };
    }

    private File changeDirectory(File currentDir, String name) {
        if (currentDir == null) {
            return new File()
                    .setName(name)
                    .setContents(new ArrayList<>())
                    .setDir(true);
        }

        if (PARENT_DIR.equals(name)) {
            return currentDir.getParent();
        }

        return currentDir.getContents().stream()
                .filter(f -> f.getName().equals(name))
                .findFirst().get();
    }

    private File listContents(File currentDir, List<String> contents) {
        for (String line : contents) {
            var parts = line.split(" ");

            File newFile = new File()
                    .setName(parts[1])
                    .setParent(currentDir);

            if (parts[0].equals(DIRECTORY)) {
                currentDir.getContents().add(newFile
                        .setContents(new ArrayList<>())
                        .setDir(true)
                );
            } else {
                currentDir.getContents().add(newFile
                        .setFileSize(Integer.parseInt(parts[0]))
                        .setDir(false));
            }
        }

        return currentDir;
    }

    private void addFileSizeInfo(File dir) {
        int totalSize = 0;

        for (File file: dir.getContents()) {
            if(file.isDir()) {
                addFileSizeInfo(file);
            }
            totalSize += file.getFileSize();
        }

        dir.setFileSize(totalSize);
    }

    public Integer solution2(List<TerminalCommand> commands) {
        File outer = prepareTreeStructure(commands);
        var dirList = getDirs(outer);

        var spaceAvailable = MAX_SPACE - outer.getFileSize();
        var spaceToClear = REQUIRED_SPACE - spaceAvailable;

        return dirList.stream()
                .map(File::getFileSize)
                .filter(d -> d >= spaceToClear)
                .min(Integer::compareTo).get();
    }
}
