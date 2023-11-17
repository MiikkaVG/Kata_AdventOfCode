package com.example.adventofcode.day7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TerminalCommand {
    private TerminalCommandType commandType;
    private List<String> data;
}
