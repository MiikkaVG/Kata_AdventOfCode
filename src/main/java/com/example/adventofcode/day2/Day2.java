package com.example.adventofcode.day2;

import com.example.adventofcode.day2.domain.Round;
import com.example.adventofcode.day2.util.Day2InputProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Day2 {
    @Autowired
    private Day2InputProvider inputProvider;

    @Autowired
    private InputToRoundFomatter inputToRoundFomatter;

    public Day2(Day2InputProvider inputProvider, InputToRoundFomatter inputToRoundFomatter) {
        this.inputProvider = inputProvider;
        this.inputToRoundFomatter = inputToRoundFomatter;
    }

    public int runSolution1() {
        return solution1(inputProvider.getInput());
    }

    public int runSolution2() {
        return solution2(inputProvider.getInput());
    }

    private int solution1(String input) {
         Map<String, String> winningCombinations = getWinningCombinations();
         Map<String, String> drawCombinations = getDrawCombinations();
         Map<String, Integer> scoresByMove = getScoresByMove();

        List<Round> rounds = inputToRoundFomatter.mapToRounds(input);

        int total = 0;

        for (Round round : rounds) {
            boolean win = winningCombinations.get(round.getOpponent()).equals(round.getYours());
            int choicePoints = scoresByMove.get(round.getYours());
            total += choicePoints;

            if (win) {
                total += 6;
            } else if (drawCombinations.get(round.getOpponent()).equals(round.getYours())) {
                total += 3;
            }
        }

        return total;
    }

    private Map<String, String> getWinningCombinations() {
        HashMap<String, String> winningCombinations = new HashMap<>();
        // a = ROCK, y = PAPER
        winningCombinations.put("A", "Y");
        // b = PAPER, z = SCISSORS
        winningCombinations.put("B", "Z");
        // c = SCISSORS, x = rock
        winningCombinations.put("C", "X");
        return winningCombinations;
    }

    private Map<String, String> getDrawCombinations() {
        HashMap<String, String> drawCombinations = new HashMap<>();
        drawCombinations.put("A", "X");
        drawCombinations.put("B", "Y");
        drawCombinations.put("C", "Z");
        return drawCombinations;
    }

    private Map<String, Integer> getScoresByMove() {
        Map<String, Integer> scoresByMove = new HashMap<>();
        scoresByMove.put("X", 1);
        scoresByMove.put("Y", 2);
        scoresByMove.put("Z", 3);
        return scoresByMove;
    }

    private int solution2(String input) {
        // TODO: write solution to part2 of the problem here, create additional classes if you want
        var rounds = inputToRoundFomatter.mapToRounds(input);
        var combos = getCombinations();

        int total = 0;
        for (Round round : rounds) {
            System.out.println(round.getOpponent());
           total += combos.get(round.getOpponent()).get(round.getYours());
        }
        return total;
//        return rounds.stream()
//                .map(round -> )
//                .reduce(0, Integer::sum);
    }

    private Map<String, Map<String, Integer>> getCombinations() {
        Map<String, Map<String, Integer>> combinations = new HashMap<>();

        var rock = new HashMap<String, Integer>();
        rock.put("X", 3);
        rock.put("Y", 1 + 3);
        rock.put("Z", 2 + 6);

        var paper = new HashMap<String, Integer>();
        paper.put("X", 1);
        paper.put("Y", 2 + 3);
        paper.put("Z", 3 + 6);

        var scissors = new HashMap<String, Integer>();
        scissors.put("X", 2);
        scissors.put("Y", 3 + 3);
        scissors.put("Z", 1 + 6);

        combinations.put("A", rock);
        combinations.put("B", paper);
        combinations.put("C", scissors);

        return combinations;
    }
}