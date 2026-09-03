package com.github.renanfranca.stringcalculator;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class StringCalculator {

  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }

    String delimiter = "[,\n]";
    String values = numbers;
    if (numbers.startsWith("//")) {
      int headerEnd = numbers.indexOf('\n');
      String delimiterSpecification = numbers.substring(2, headerEnd);
      if (delimiterSpecification.startsWith("[")) {
        delimiter = Pattern.compile("\\[([^]]+)]")
          .matcher(delimiterSpecification)
          .results()
          .map(result -> Pattern.quote(result.group(1)))
          .collect(Collectors.joining("|"));
      } else {
        delimiter = Pattern.quote(delimiterSpecification);
      }
      values = numbers.substring(headerEnd + 1);
    }

    int[] parsedNumbers = Arrays.stream(values.split(delimiter)).mapToInt(Integer::parseInt).toArray();
    String negatives = Arrays.stream(parsedNumbers)
      .filter(number -> number < 0)
      .mapToObj(Integer::toString)
      .collect(Collectors.joining(","));
    if (!negatives.isEmpty()) {
      throw new IllegalArgumentException("Negatives not allowed: " + negatives);
    }

    return Arrays.stream(parsedNumbers).filter(number -> number <= 1000).sum();
  }
}
