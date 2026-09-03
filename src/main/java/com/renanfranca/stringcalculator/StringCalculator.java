package com.renanfranca.stringcalculator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }

    ParsedInput input = parseInput(numbers);
    int[] parsedNumbers = Arrays.stream(input.values().split(input.delimiterPattern())).mapToInt(Integer::parseInt).toArray();
    rejectNegatives(parsedNumbers);

    return Arrays.stream(parsedNumbers).filter(number -> number <= 1000).sum();
  }

  private ParsedInput parseInput(String numbers) {
    String delimiterPattern = "[,\\n]";
    String values = numbers;
    if (numbers.startsWith("//")) {
      int headerEnd = numbers.indexOf('\n');
      String delimiter = numbers.substring(2, headerEnd);
      if (delimiter.startsWith("[") && delimiter.endsWith("]")) {
        delimiterPattern = Arrays.stream(delimiter.substring(1, delimiter.length() - 1).split("\\]\\["))
          .sorted(Comparator.comparingInt(String::length).reversed())
          .map(Pattern::quote)
          .collect(Collectors.joining("|"));
      } else {
        delimiterPattern = Pattern.quote(delimiter);
      }
      values = numbers.substring(headerEnd + 1);
    }

    return new ParsedInput(values, delimiterPattern);
  }

  private void rejectNegatives(int[] parsedNumbers) {
    String negatives = Arrays.stream(parsedNumbers)
      .filter(number -> number < 0)
      .mapToObj(Integer::toString)
      .collect(Collectors.joining(","));
    if (!negatives.isEmpty()) {
      throw new IllegalArgumentException("Negatives not allowed: " + negatives);
    }
  }

  private record ParsedInput(String values, String delimiterPattern) {}
}
