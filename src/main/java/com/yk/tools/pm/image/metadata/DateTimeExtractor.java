package com.yk.tools.pm.image.metadata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

/**
 * Extracts LocalDateTime from image metadata
 */
public final class DateTimeExtractor {

  private static final List<String> PRIORITY_KEYS = List.of(
      "Creation Date", "Created", "Creation Time",
      "Modification Date", "Modified", "Update Date",
      "Date/Time Original", "Date/Time Digitized",
      "Date/Time", "File Modified Date"
  );

  private static final List<DateTimeFormatter> DATE_FORMATTERS = List.of(
      DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ssX"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss.SSS"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss.SSSX"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ss"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ssX"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ss.SSS"),
      DateTimeFormatter.ofPattern("yyyy:MM:dd'T'HH:mm:ss.SSSX")
  );

  private DateTimeExtractor() {
    throw new AssertionError("Insance is not allowed.");
  }

  public static LocalDateTime extractDateTime(Map<String, String> metadata) {
    // 1. Try prioritized keys
    for (String key : PRIORITY_KEYS) {
      if (metadata.containsKey(key)) {
        LocalDateTime parsedDateTime = parseDateTime(metadata.get(key));
        if (parsedDateTime != null) {
          return parsedDateTime;
        }
      }
    }

    // 2. Search for any key that contains "date"
    for (Map.Entry<String, String> entry : metadata.entrySet()) {
      if (entry.getKey().toLowerCase().contains("date")) {
        LocalDateTime parsedDateTime = parseDateTime(entry.getValue());
        if (parsedDateTime != null) {
          return parsedDateTime;
        }
      }
    }

    // 3. Nothing found
    return null;
  }

  private static LocalDateTime parseDateTime(String dateStr) {
    for (DateTimeFormatter formatter : DATE_FORMATTERS) {
      try {
        return LocalDateTime.parse(dateStr, formatter);
      } catch (DateTimeParseException ignored) {
        // Try next format
      }
    }

    // Fallback: parse as ZonedDateTime then convert to LocalDateTime
    try {
      return LocalDateTime.parse(dateStr, DateTimeFormatter.RFC_1123_DATE_TIME);
    } catch (DateTimeParseException ignored) {
      // ignore
    }

    return null;
  }
}
