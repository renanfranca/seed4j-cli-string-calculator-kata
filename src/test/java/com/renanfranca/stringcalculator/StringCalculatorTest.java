package com.renanfranca.stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

  @Test
  void returnsZeroForAnEmptyString() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("");

    assertThat(result).isZero();
  }

  @Test
  void returnsTheOnlyNumber() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("7");

    assertThat(result).isEqualTo(7);
  }

  @Test
  void addsTwoCommaSeparatedNumbers() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1,2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void addsAnUnknownAmountOfNumbers() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1,2,3,4");

    assertThat(result).isEqualTo(10);
  }

  @Test
  void acceptsNewLinesAsDelimiters() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1\n2,3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void acceptsASingleCustomDelimiter() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//;\n1;2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void rejectsNegativeNumbersWithTheirValues() {
    StringCalculator calculator = new StringCalculator();

    assertThatThrownBy(() -> calculator.add("-1,2"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Negatives not allowed: -1");
  }

  @Test
  void reportsAllNegativeNumbersInInputOrder() {
    StringCalculator calculator = new StringCalculator();

    assertThatThrownBy(() -> calculator.add("2,-4,3,-5"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Negatives not allowed: -4,-5");
  }

  @Test
  void ignoresNumbersGreaterThanOneThousand() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1001,2");

    assertThat(result).isEqualTo(2);
  }

  @Test
  void acceptsACustomDelimiterOfAnyLength() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//[|||]\n1|||2|||3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void acceptsMultipleCustomDelimiters() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//[|][%]\n1|2%3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void acceptsMultipleCustomDelimitersOfAnyLength() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//[***][%%]\n1***2%%3");

    assertThat(result).isEqualTo(6);
  }
}
