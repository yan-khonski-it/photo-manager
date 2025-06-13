package com.yk.tools.pm.image.metadata;

import com.yk.tools.pm.utils.DateTimeParser;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Extracts LocalDateTime from image metadata
 */
public final class DateTimeExtractor {

  private static final Logger LOGGER = LogManager.getLogger(DateTimeExtractor.class);

  private static final List<String> PRIORITY_KEYS = List.of(
      "Date/Time",
      "Date/Time Original",
      "Date/Time Digitized",
      "Creation Date",
      "Created",
      "Creation Time",
      "Modification Date",
      "Modified",
      "Update Date",
      "File Modified Date"
  );

  private DateTimeExtractor() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static LocalDateTime extractDateTime(Map<String, String> metadata) {
    // 1. Try prioritized keys
    for (String key : PRIORITY_KEYS) {
      if (metadata.containsKey(key)) {
        LocalDateTime parsedDateTime = DateTimeParser.parseDateTime(metadata.get(key));
        if (parsedDateTime != null) {
          return parsedDateTime;
        }
      }
    }

    // 2. Search for any key that contains "date"
    for (Map.Entry<String, String> entry : metadata.entrySet()) {
      if (entry.getKey().toLowerCase().contains("date")) {
        LocalDateTime parsedDateTime = DateTimeParser.parseDateTime(entry.getValue());
        if (parsedDateTime != null) {
          return parsedDateTime;
        }
      }
    }

    // 3. Nothing found
    LOGGER.warn("No date time parameter found in metadata.\n{}", metadata);
    return null;
  }
}
