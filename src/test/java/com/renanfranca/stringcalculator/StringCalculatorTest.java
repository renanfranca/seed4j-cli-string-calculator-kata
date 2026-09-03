package com.renanfranca.stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

  @Test
  void shouldReturnZeroForEmptyInput() {
    var calculator = new StringCalculator();

    int result = calculator.add("");

    assertThat(result).isZero();
  }

  @Test
  void shouldReturnTheOnlyNumber() {
    var calculator = new StringCalculator();

    int result = calculator.add("7");

    assertThat(result).isEqualTo(7);
  }

  @Test
  void shouldAddTwoNumbers() {
    var calculator = new StringCalculator();

    int result = calculator.add("1,2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void shouldAddAnyAmountOfNumbers() {
    var calculator = new StringCalculator();

    int result = calculator.add("1,2,3,4");

    assertThat(result).isEqualTo(10);
  }

  @Test
  void shouldAcceptNewLinesAsSeparators() {
    var calculator = new StringCalculator();

    int result = calculator.add("1\n2,3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void shouldAcceptACustomDelimiter() {
    var calculator = new StringCalculator();

    int result = calculator.add("//;\n1;2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void shouldRejectANegativeNumber() {
    var calculator = new StringCalculator();

    assertThatThrownBy(() -> calculator.add("-1,2"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Negatives not allowed: -1");
  }

  @Test
  void shouldReportEveryNegativeNumber() {
    var calculator = new StringCalculator();

    assertThatThrownBy(() -> calculator.add("2,-4,3,-5"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Negatives not allowed: -4,-5");
  }

  @Test
  void shouldIgnoreNumbersGreaterThanOneThousand() {
    var calculator = new StringCalculator();

    int result = calculator.add("1001,2");

    assertThat(result).isEqualTo(2);
  }

  @Test
  void shouldAcceptADelimiterOfAnyLength() {
    var calculator = new StringCalculator();

    int result = calculator.add("//[|||]\n1|||2|||3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void shouldAcceptMultipleDelimiters() {
    var calculator = new StringCalculator();

    int result = calculator.add("//[|][%]\n1|2%3");

    assertThat(result).isEqualTo(6);
  }

  @Test
  void shouldAcceptMultipleDelimitersOfAnyLength() {
    var calculator = new StringCalculator();

    int result = calculator.add("//[*][**]\n1**2*3");

    assertThat(result).isEqualTo(6);
  }
}
