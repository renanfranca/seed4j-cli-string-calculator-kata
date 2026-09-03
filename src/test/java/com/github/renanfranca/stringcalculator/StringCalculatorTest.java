package com.github.renanfranca.stringcalculator;

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
  void shouldReturnTheSingleNumber() {
    var calculator = new StringCalculator();

    int result = calculator.add("7");

    assertThat(result).isEqualTo(7);
  }

  @Test
  void shouldAddTwoCommaSeparatedNumbers() {
    var calculator = new StringCalculator();

    int result = calculator.add("1,2");

    assertThat(result).isEqualTo(3);
  }

  @Test
  void shouldAddAnyAmountOfNumbers() {
    var calculator = new StringCalculator();

    int result = calculator.add("1,2,3,4,5");

    assertThat(result).isEqualTo(15);
  }

  @Test
  void shouldAcceptNewlinesAsSeparators() {
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
  void shouldRejectAllNegativeNumbers() {
    var calculator = new StringCalculator();

    var result = assertThatThrownBy(() -> calculator.add("2,-4,3,-5"));

    result.isInstanceOf(IllegalArgumentException.class).hasMessage("Negatives not allowed: -4,-5");
  }

  @Test
  void shouldIgnoreNumbersGreaterThanOneThousand() {
    var calculator = new StringCalculator();

    int result = calculator.add("2,1000,1001");

    assertThat(result).isEqualTo(1002);
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

    int result = calculator.add("//[**][%%%]\n1**2%%%3");

    assertThat(result).isEqualTo(6);
  }
}
