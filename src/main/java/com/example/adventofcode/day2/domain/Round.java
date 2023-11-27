package com.example.adventofcode.day2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Round {
    private String opponent;
    private String yours;
}
