package com.yk.tools.pm.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class NumberUtilsTest {

  @ParameterizedTest
  @CsvSource({
      // Valid: Only one number group
      "'4000',               4000",
      "'pixels 4000 super',  4000",
      "  pixels 4000 super , 4000",
      "pixels 4000 super,    4000",
      "'foo 123',            123",
      "'   42   ',           42",
      "'-123',              -123",
      "'start -88 end',     -88",
      "'value: 0',            0",
      "'just 999 pixels',   999",
      // Valid: One number at start
      "'123px',              123",
      "'-321px',            -321"
  })
  void shouldExtractSingleIntGroup(String input, int expected) {
    Integer result = NumberUtils.retrieveIntValue(input);
    assertThat(result).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
      // Invalid: multiple digit groups
      "'123dd33'",
      "'foo 123 bar 456'",
      "'pixels 500 400 super'",
      "'-2 9'",
      "'-2foo9'",
      // Invalid: no digit group
      "'abc'",
      "'no numbers here'",
      "'   '",
      "'!@#$%'",
      "''",
      // Invalid: null
      ",",
      // Invalid decimal.
      "99.99"
  })
  void shouldReturnNullForMultipleOrNoGroups(String input) {
    Integer result = NumberUtils.retrieveIntValue(input);
    assertThat(result).isNull();
  }

  @Test
  void shouldReturnNullForBlankString() {
    assertThat(NumberUtils.retrieveIntValue(" ")).isNull();
    assertThat(NumberUtils.retrieveIntValue(null)).isNull();
  }
}