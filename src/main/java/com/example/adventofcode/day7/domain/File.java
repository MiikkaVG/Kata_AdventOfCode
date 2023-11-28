package com.example.adventofcode.day7.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class File {
    private String name;
    private File parent;
    private List<File> contents;
    private Integer fileSize;
    private boolean isDir;
}
