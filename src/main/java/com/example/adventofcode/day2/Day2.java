package com.example.adventofcode.day2;

import com.example.adventofcode.day2.util.Day2InputProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Day2 {
    @Autowired
    private Day2InputProvider inputProvider;

    public Integer runSolution1() {
        return solution1(inputProvider.getInput());
    }
    public Integer runSolution2() {
        return solution2(inputProvider.getInput());
    }


    private Integer solution1(String input) {
        // TODO: write solution to part1 of the problem here, create additional classes if you want
        System.out.println(input);
        return 1;
    }

    private Integer solution2(String input) {
        // TODO: write solution to part2 of the problem here, create additional classes if you want

        return 1;
    }
}
