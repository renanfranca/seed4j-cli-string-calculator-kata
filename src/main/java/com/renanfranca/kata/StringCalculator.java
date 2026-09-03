package com.renanfranca.kata;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }

    String delimiterPattern = ",|\n";
    if (numbers.startsWith("//")) {
      int headerEnd = numbers.indexOf('\n');
      String delimiterDefinition = numbers.substring(2, headerEnd);
      if (delimiterDefinition.startsWith("[")) {
        delimiterPattern = Arrays.stream(
                delimiterDefinition.substring(1, delimiterDefinition.length() - 1).split("\\]\\["))
            .map(Pattern::quote)
            .collect(Collectors.joining("|"));
      } else {
        delimiterPattern = Pattern.quote(delimiterDefinition);
      }
      numbers = numbers.substring(headerEnd + 1);
    }

    int[] parsedNumbers = Arrays.stream(numbers.split(delimiterPattern))
        .mapToInt(Integer::parseInt)
        .toArray();
    String negatives = Arrays.stream(parsedNumbers)
        .filter(number -> number < 0)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining(","));

    if (!negatives.isEmpty()) {
      throw new IllegalArgumentException("Negatives not allowed: " + negatives);
    }

    return Arrays.stream(parsedNumbers)
        .filter(number -> number <= 1000)
        .sum();
  }
}
