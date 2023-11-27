package com.example.adventofcode.day7;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Day7Test {
    private static final int SOLUTION_1 = 1297159;
    private static final int SOLUTION_2 = 3866390;

    @Autowired
    private Day7 day7;


    @Test
    void solution1() {
        assertThat(day7.runSolution1()).isEqualTo(SOLUTION_1);
    }

    @Test
    void solution2() {
        assertThat(day7.runSolution2()).isEqualTo(SOLUTION_2);
    }

}
