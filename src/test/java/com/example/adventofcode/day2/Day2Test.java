package com.example.adventofcode.day2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class Day2Test {
    @Autowired
    private Day2 day2;

    private static final int SOLUTION_1 = 9651;
    private static final int SOLUTION_2 = 10560;


    @Test
    void testSolution1() {
        assertThat(day2.runSolution1()).isEqualTo(SOLUTION_1);
    }

    @Test
    void testSolution2() {
        assertThat(day2.runSolution2()).isEqualTo(SOLUTION_2);
    }
}