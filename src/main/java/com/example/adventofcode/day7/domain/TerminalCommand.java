package com.example.adventofcode.day7.domain;

import java.util.List;

public record TerminalCommand (TerminalCommandType commandType, List<String> data){
}
