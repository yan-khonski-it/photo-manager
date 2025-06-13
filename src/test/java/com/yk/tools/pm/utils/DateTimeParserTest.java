package com.yk.tools.pm.utils;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class DateTimeParserTest {

  private static Stream<Arguments> provideLocalDateTimeFormats() {
    return Stream.of(
        // yyyy:MM:dd HH:mm:ss
        Arguments.of("2023:05:15 14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("   2023:05:15 14:30:45  ", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),

        // yyyy:MM:dd HH:mm:ss.SSS
        Arguments.of("2023:05:15 14:30:45.123", LocalDateTime.of(2023, 5, 15, 14, 30, 45, 123_000_000)),

        // yyyy:MM:dd'T'HH:mm:ss
        Arguments.of("2023:05:15T14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),

        // yyyy:MM:dd'T'HH:mm:ss.SSS
        Arguments.of("2023:05:15T14:30:45.123", LocalDateTime.of(2023, 5, 15, 14, 30, 45, 123_000_000)),
        Arguments.of("2023:05:15T14:30:45.123", LocalDateTime.of(2023, 5, 15, 14, 30, 45, 123_000_000)),

        Arguments.of("2023/05/15 14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("2023-05-15 14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("2023.05.15 14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("2023/05/15T14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("2023-05-15T14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("2023.05.15T14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45))
    );
  }

  private static Stream<Arguments> provideZonedDateTimeFormats() {
    return Stream.of(
        // yyyy:MM:dd HH:mm:ssX
        Arguments.of("2023:05:15 14:30:45Z", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),

        // yyyy:MM:dd HH:mm:ss.SSSX
        Arguments.of("2023:05:15 14:30:45.123Z", LocalDateTime.of(2023, 5, 15, 14, 30, 45, 123_000_000)),

        // yyyy:MM:dd'T'HH:mm:ssX
        Arguments.of("2023:05:15T14:30:45Z", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),

        // yyyy:MM:dd'T'HH:mm:ss.SSSX
        Arguments.of("2023:05:15T14:30:45.123Z", LocalDateTime.of(2023, 5, 15, 14, 30, 45, 123_000_000)),

        // EEE MMM dd HH:mm:ss XXX yyyy
        Arguments.of("Mon May 15 14:30:45 UTC 2023", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),

        Arguments.of("      Mon May 15 14:30:45 UTC 2023   ", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),

        // RFC_1123_DATE_TIME
        Arguments.of("Mon, 15 May 2023 14:30:45 GMT", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),

        Arguments.of(" Mon, 15 May 2023 14:30:45 GMT ", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("Mon May 15 14:30:45 UTC 2023", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("Mon May 15 14:30:45 GMT 2023", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("Mon May 15 14:30:45 PST 2023", LocalDateTime.of(2023, 5, 15, 14, 30, 45))
    );
  }

  @ParameterizedTest
  @MethodSource("provideLocalDateTimeFormats")
  void shouldParseLocalDateTimeFormats(String dateStr, LocalDateTime expected) {
    LocalDateTime result = DateTimeParser.parseDateTime(dateStr);
    assertThat(result).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("provideZonedDateTimeFormats")
  void shouldParseZonedDateTimeFormats(String dateStr, LocalDateTime expected) {
    LocalDateTime result = DateTimeParser.parseDateTime(dateStr);
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void shouldReturnNullForInvalidInputs() {
    assertThat(DateTimeParser.parseDateTime(null)).isNull();
    assertThat(DateTimeParser.parseDateTime("")).isNull();
    assertThat(DateTimeParser.parseDateTime(" ")).isNull();
    assertThat(DateTimeParser.parseDateTime("null")).isNull();
    assertThat(DateTimeParser.parseDateTime("Monday, 15 May 2023 14:30:45 UTC")).isNull();
  }

  @Test
  void shouldReturnNullForUnsupportedFormat() {
    assertThat(DateTimeParser.parseDateTime("2023/05/15 14:30:45 UTC")).isNull();
    assertThat(DateTimeParser.parseDateTime("2023/05/15T14:30:45 UTC")).isNull();
  }
}