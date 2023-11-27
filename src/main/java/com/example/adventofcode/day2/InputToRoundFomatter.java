package com.example.adventofcode.day2;

import com.example.adventofcode.day2.domain.Round;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InputToRoundFomatter {

    public List<Round> mapToRounds(String input) {
        return Arrays.stream(input.split("\n"))
                .map(String::trim)
                .map(this::lineToRound)
                .collect(Collectors.toList());
    }

    private Round lineToRound(String line) {
        String[] parts = line.split(" ");
        return new Round(parts[0], parts[1]);
    }
}
