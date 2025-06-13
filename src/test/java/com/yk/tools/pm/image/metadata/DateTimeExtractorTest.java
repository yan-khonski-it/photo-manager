package com.yk.tools.pm.image.metadata;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DateTimeExtractorTest {

  private static Stream<Arguments> providePriorityKeyTestCases() {
    return Stream.of(
        Arguments.of("Date/Time", "2023:05:15 14:30:45", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),
        Arguments.of("Date/Time Original", "2022:01:02T03:04:05", LocalDateTime.of(2022, 1, 2, 3, 4, 5)),
        Arguments.of("File Modified Date", "Mon, 15 May 2023 14:30:45 GMT", LocalDateTime.of(2023, 5, 15, 14, 30, 45)),

        Arguments.of("Date/Time Digitized", "2021:12:11 23:12:01", LocalDateTime.of(2021, 12, 11, 23, 12, 1)),
        Arguments.of("Creation Date", "2020-10-20 11:22:33", LocalDateTime.of(2020, 10, 20, 11, 22, 33)),
        Arguments.of("Created", "2019/03/04T09:08:07", LocalDateTime.of(2019, 3, 4, 9, 8, 7)),
        Arguments.of("Creation Time", "2018.07.08 06:05:04", LocalDateTime.of(2018, 7, 8, 6, 5, 4)),
        Arguments.of("Modification Date", "2017:05:15 15:45:35", LocalDateTime.of(2017, 5, 15, 15, 45, 35)),
        Arguments.of("Modified", "2016:01:01 00:00:00", LocalDateTime.of(2016, 1, 1, 0, 0, 0)),
        Arguments.of("Update Date", "2015:12:31 23:59:59", LocalDateTime.of(2015, 12, 31, 23, 59, 59))
    );
  }

  // Parameterized: All supported priority keys, different formats
  @ParameterizedTest
  @MethodSource("providePriorityKeyTestCases")
  void shouldExtractDateTimeFromPriorityKeys(String key, String value, LocalDateTime expected) {
    Map<String, String> metadata = new HashMap<>();
    metadata.put(key, value);

    LocalDateTime actual = DateTimeExtractor.extractDateTime(metadata);

    assertThat(actual).isEqualTo(expected);
  }

  // Fallback: no priority key, use any key containing "date"
  @Test
  void shouldExtractDateFromNonPriorityKeyContainingDate() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("Custom Date Field", "2023:05:15 14:30:45");
    LocalDateTime expected = LocalDateTime.of(2023, 5, 15, 14, 30, 45);

    LocalDateTime actual = DateTimeExtractor.extractDateTime(metadata);

    assertThat(actual).isEqualTo(expected);
  }

  // Null when nothing parseable
  @Test
  void shouldReturnNullWhenNoValidDate() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("Aperture", "f/2.8");
    metadata.put("ISO", "100");
    metadata.put("Date/Time", "invalid-date-format");

    assertThat(DateTimeExtractor.extractDateTime(metadata)).isNull();
    assertThat(DateTimeExtractor.extractDateTime(new HashMap<>())).isNull();
  }

  // Correct priority: first match wins
  @Test
  void shouldRespectPriorityOrder() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("Modification Date", "2023:06:20 10:15:30");  // Lower priority
    metadata.put("Date/Time Original", "2023:05:15 14:30:45"); // Higher priority
    metadata.put("Update Date", "2015:12:31 23:59:59");

    LocalDateTime expected = LocalDateTime.of(2023, 5, 15, 14, 30, 45);

    assertThat(DateTimeExtractor.extractDateTime(metadata)).isEqualTo(expected);
  }

  // Skip invalid and pick next valid key in priority
  @Test
  void shouldSkipInvalidAndPickNextValidPriorityKey() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("Date/Time Original", "invalid-date");
    metadata.put("Date/Time", "2023:06:20 10:15:30");

    LocalDateTime expected = LocalDateTime.of(2023, 6, 20, 10, 15, 30);

    assertThat(DateTimeExtractor.extractDateTime(metadata)).isEqualTo(expected);
  }

  // Check priority keys before "any" date key
  @Test
  void shouldCheckAllPriorityKeysBeforeAnyDateKey() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("File Modified Date", "2023:06:20 10:15:30");
    metadata.put("Some Date Info", "2023:05:15 14:30:45");

    LocalDateTime expected = LocalDateTime.of(2023, 6, 20, 10, 15, 30);

    assertThat(DateTimeExtractor.extractDateTime(metadata)).isEqualTo(expected);
  }

  // All priority keys unparseable, fallback to generic
  @Test
  void shouldFallbackToGenericDateKeyIfAllPriorityKeysUnparseable() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("Date/Time", "bad");
    metadata.put("File Modified Date", "worse");
    metadata.put("Secondary date key", "2023/05/15T14:30:45"); // supported

    LocalDateTime expected = LocalDateTime.of(2023, 5, 15, 14, 30, 45);

    assertThat(DateTimeExtractor.extractDateTime(metadata)).isEqualTo(expected);
  }

  // Real-world sample
  @Test
  void shouldExtractDateFromSonyZVE1Metadata() {
    Map<String, String> metadata = SonyZV_E1_MetadataSample.getSonyZVE1Metadata();
    LocalDateTime expected = LocalDateTime.of(2025, 2, 1, 0, 46, 59);

    assertThat(DateTimeExtractor.extractDateTime(metadata)).isEqualTo(expected);
  }
}
