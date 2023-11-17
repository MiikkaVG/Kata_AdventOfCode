package com.example.adventofcode.day7;

import com.example.adventofcode.day7.domain.TerminalCommand;
import com.example.adventofcode.day7.util.Day7InputProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Day7 {
    @Autowired
    private Day7InputProvider inputProvider;

    public Integer runSolution1() {
        return solution1(inputProvider.getInput());
    }
    public Integer runSolution2() {
        return solution2(inputProvider.getInput());
    }


    public Integer solution1(List<TerminalCommand> commands) {
        // TODO: write solution to part1 of the problem here, create additional classes if you want
        for(var command: commands) {
            System.out.println(command.getCommandType().name());

            for(var entry: command.getData()) {
                System.out.println("\tDATA: "+entry);
            }
        }
        return 1;
    }

    public Integer solution2(List<TerminalCommand> commands) {
        // TODO: write solution to part2 of the problem here, create additional classes if you want

        return 1;
    }


}
