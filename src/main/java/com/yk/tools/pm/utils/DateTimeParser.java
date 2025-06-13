package com.yk.tools.pm.utils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DateTimeParser {

  private static final Logger LOGGER = LogManager.getLogger(DateTimeParser.class);

  @SuppressWarnings("PMD.LongVariable")
  private static final List<DateTimeFormatter> LOCAL_DATE_FORMATTERS = List.of(
      DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss.SSS"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ss.SSS"),
      DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy.MM.dd'T'HH:mm:ss")
  );

  @SuppressWarnings("PMD.LongVariable")
  private static final List<DateTimeFormatter> ZONED_DATE_FORMATTERS = List.of(
      DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ssX"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss.SSSX"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ssX"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ss.SSSX"),
      DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss XXX yyyy", Locale.ENGLISH),
      DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH),
      DateTimeFormatter.RFC_1123_DATE_TIME
  );

  private DateTimeParser() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static LocalDateTime parseDateTime(String dateStr) {
    if (StringUtils.isBlank(dateStr)) {
      return null;
    }

    String dateStrTrimmed = dateStr.trim();
    // Try local-only formats
    for (DateTimeFormatter formatter : LOCAL_DATE_FORMATTERS) {
      try {
        return LocalDateTime.parse(dateStrTrimmed, formatter);
      } catch (DateTimeParseException ignored) {
      }
    }

    // Try zoned formats, then convert
    for (DateTimeFormatter formatter : ZONED_DATE_FORMATTERS) {
      try {
        return ZonedDateTime.parse(dateStrTrimmed, formatter).toLocalDateTime();
      } catch (DateTimeParseException ignored) {
      }
    }

    LOGGER.warn("Unable to parse date time string: {}.", dateStrTrimmed);
    return null;
  }
}
