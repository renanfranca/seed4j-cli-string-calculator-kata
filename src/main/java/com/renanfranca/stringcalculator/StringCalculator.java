package com.renanfranca.stringcalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class StringCalculator {

  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }

    String values = numbers;
    String delimiterPattern = "[,\\n]";
    if (numbers.startsWith("//")) {
      int headerEnd = numbers.indexOf('\n');
      delimiterPattern = buildDelimiterPattern(numbers.substring(2, headerEnd));
      values = numbers.substring(headerEnd + 1);
    }

    int total = 0;
    StringJoiner negatives = new StringJoiner(",");
    for (String number : values.split(delimiterPattern)) {
      int value = Integer.parseInt(number);
      if (value < 0) {
        negatives.add(String.valueOf(value));
      }
      if (value <= 1000) {
        total += value;
      }
    }

    if (negatives.length() > 0) {
      throw new IllegalArgumentException("Negatives not allowed: " + negatives);
    }

    return total;
  }

  private static String buildDelimiterPattern(String definition) {
    if (!definition.startsWith("[")) {
      return Pattern.quote(definition);
    }

    List<String> delimiters = new ArrayList<>();
    for (int start = 0; start < definition.length();) {
      int end = definition.indexOf(']', start);
      delimiters.add(definition.substring(start + 1, end));
      start = end + 1;
    }

    delimiters.sort(Comparator.comparingInt(String::length).reversed());
    StringJoiner alternatives = new StringJoiner("|");
    for (String delimiter : delimiters) {
      alternatives.add(Pattern.quote(delimiter));
    }
    return alternatives.toString();
  }
}
