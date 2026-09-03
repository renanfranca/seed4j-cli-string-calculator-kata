package com.renanfranca.stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class StringCalculator {

  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }

    String delimiterPattern = "[,\\n]";
    if (numbers.startsWith("//")) {
      int headerEnd = numbers.indexOf('\n');
      String delimiter = numbers.substring(2, headerEnd);
      if (delimiter.startsWith("[")) {
        String delimiterDefinition = delimiter.substring(1, delimiter.length() - 1);
        if (delimiterDefinition.contains("][")) {
          StringJoiner alternatives = new StringJoiner("|");
          String[] candidates = delimiterDefinition.split("\\]\\[");
          Arrays.sort(candidates, (left, right) -> Integer.compare(right.length(), left.length()));
          for (String candidate : candidates) {
            alternatives.add(Pattern.quote(candidate));
          }
          delimiterPattern = alternatives.toString();
        } else {
          delimiterPattern = Pattern.quote(delimiterDefinition);
        }
      } else {
        delimiterPattern = Pattern.quote(delimiter);
      }
      numbers = numbers.substring(headerEnd + 1);
    }

    String[] operands = numbers.split(delimiterPattern);
    int total = 0;
    List<String> negatives = new ArrayList<>();
    for (String operand : operands) {
      int number = Integer.parseInt(operand);
      if (number < 0) {
        negatives.add(operand);
      }
      if (number <= 1000) {
        total += number;
      }
    }

    if (!negatives.isEmpty()) {
      throw new IllegalArgumentException("Negatives not allowed: " + String.join(",", negatives));
    }

    return total;
  }
}
