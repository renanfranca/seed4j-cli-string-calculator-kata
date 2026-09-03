package com.renanfranca.stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

  @Test
  void shouldReturnZeroForEmptyInput() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("");

    assertThat(result).isZero();
  }

  @Test
  void shouldReturnTheSingleNumber() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("7");

    assertThat(result).isEqualTo(7);
  }

  @Test
  void shouldAddTwoCommaSeparatedNumbers() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1,2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void shouldAddAnyAmountOfNumbers() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1,2,3,4");

    assertThat(result).isEqualTo(10);
  }

  @Test
  void shouldTreatNewlinesAsDelimiters() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1\n2,3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void shouldSupportACustomDelimiter() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//;\n1;2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void shouldRejectANegativeNumber() {
    StringCalculator calculator = new StringCalculator();

    assertThatThrownBy(() -> calculator.add("-1,2"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Negatives not allowed: -1");
  }

  @Test
  void shouldReportAllNegativeNumbers() {
    StringCalculator calculator = new StringCalculator();

    assertThatThrownBy(() -> calculator.add("2,-4,3,-5"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Negatives not allowed: -4,-5");
  }

  @Test
  void shouldIgnoreNumbersGreaterThanOneThousand() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("1001,2");

    assertThat(result).isEqualTo(2);
  }

  @Test
  void shouldSupportADelimiterOfAnyLength() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//[|||]\n1|||2|||3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void shouldSupportMultipleDelimiters() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//[|][%]\n1|2%3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void shouldSupportMultipleDelimitersOfAnyLength() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//[***][%%]\n1***2%%3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void shouldSupportOverlappingDelimiters() {
    StringCalculator calculator = new StringCalculator();

    int result = calculator.add("//[*][**]\n1**2*3");

    assertThat(result).isEqualTo(6);
  }
}
