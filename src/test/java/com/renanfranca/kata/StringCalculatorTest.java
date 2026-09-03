package com.renanfranca.kata;

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

    int result = calculator.add("1");

    assertThat(result).isEqualTo(1);
  }

  @Test
  void addsTwoCommaSeparatedNumbers() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1,2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void addsAnUnknownAmountOfCommaSeparatedNumbers() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1,2,3,4");

    assertThat(result).isEqualTo(10);
  }

  @Test
  void acceptsNewLinesAsSeparators() {
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
  void rejectsANegativeNumber() {
    StringCalculator calculator = new StringCalculator();

    assertThatThrownBy(() -> calculator.add("-1,2"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Negatives not allowed: -1");
  }

  @Test
  void reportsEveryNegativeNumber() {
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

    int result = calculator.add("//[***][%]\n1***2%3");

    assertThat(result).isEqualTo(6);
  }
}
