package com.renanfranca.stringcalculator;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class StringCalculator {

  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }

    var values = parse(numbers);
    rejectNegatives(values);

    return Arrays.stream(values).filter(number -> number <= 1000).sum();
  }

  private int[] parse(String numbers) {
    var input = parseInput(numbers);
    return Arrays.stream(input.numbers().split(input.separatorPattern())).mapToInt(Integer::parseInt).toArray();
  }

  private ParsedInput parseInput(String numbers) {
    if (!numbers.startsWith("//")) {
      return new ParsedInput(numbers, "[,\\n]");
    }

    var headerEnd = numbers.indexOf('\n');
    var delimiterDefinition = numbers.substring(2, headerEnd);
    var separatorPattern = delimiterPattern(delimiterDefinition);
    return new ParsedInput(numbers.substring(headerEnd + 1), separatorPattern);
  }

  private String delimiterPattern(String definition) {
    if (!definition.startsWith("[")) {
      return Pattern.quote(definition);
    }

    var delimiters = definition.substring(1, definition.length() - 1).split("\\]\\[");
    return Arrays.stream(delimiters).map(Pattern::quote).collect(Collectors.joining("|"));
  }

  private void rejectNegatives(int[] values) {
    var negatives = Arrays.stream(values)
      .filter(number -> number < 0)
      .mapToObj(String::valueOf)
      .collect(Collectors.joining(","));
    if (!negatives.isEmpty()) {
      throw new IllegalArgumentException("Negatives not allowed: " + negatives);
    }
  }

  private record ParsedInput(String numbers, String separatorPattern) {}
}
