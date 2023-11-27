package com.example.adventofcode.day2.util;

import com.example.adventofcode.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Day2InputProvider {

    @Value("${classpath:/input/day2/input.txt}")
    private Resource inputResource;

    @Autowired
    private ResourceReader resourceReader;

    public String getInput() {
        System.out.println(resourceReader.readInputToString(inputResource));
        return resourceReader.readInputToString(inputResource);
    }
}
