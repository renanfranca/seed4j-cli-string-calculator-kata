package com.renanfranca.stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

  @Test
  void returnsZeroForEmptyInput() {
    var calculator = new StringCalculator();

    var result = calculator.add("");

    assertThat(result).isZero();
  }

  @Test
  void returnsTheOnlyNumber() {
    var calculator = new StringCalculator();

    var result = calculator.add("7");

    assertThat(result).isEqualTo(7);
  }

  @Test
  void addsTwoNumbers() {
    var calculator = new StringCalculator();

    var result = calculator.add("3,5");

    assertThat(result).isEqualTo(8);
  }

  @Test
  void addsAnyAmountOfNumbers() {
    var calculator = new StringCalculator();

    var result = calculator.add("1,2,3,4");

    assertThat(result).isEqualTo(10);
  }

  @Test
  void acceptsNewLinesAsSeparators() {
    var calculator = new StringCalculator();

    var result = calculator.add("1\n2,3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void acceptsACustomDelimiter() {
    var calculator = new StringCalculator();

    var result = calculator.add("//;\n1;2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void rejectsEveryNegativeNumberInOneMessage() {
    var calculator = new StringCalculator();

    assertThatThrownBy(() -> calculator.add("2,-4,3,-5"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Negatives not allowed: -4,-5");
  }

  @Test
  void ignoresNumbersGreaterThanOneThousand() {
    var calculator = new StringCalculator();

    var result = calculator.add("1000,1001,2");

    assertThat(result).isEqualTo(1002);
  }

  @Test
  void acceptsADelimiterOfAnyLength() {
    var calculator = new StringCalculator();

    var result = calculator.add("//[|||]\n1|||2|||3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void acceptsMultipleDelimiters() {
    var calculator = new StringCalculator();

    var result = calculator.add("//[|][%]\n1|2%3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void acceptsMultipleDelimitersOfAnyLength() {
    var calculator = new StringCalculator();

    var result = calculator.add("//[***][%%]\n1***2%%3");

    assertThat(result).isEqualTo(6);
  }
}
