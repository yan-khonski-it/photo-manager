package com.yk.tools.pm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NumberUtils {

  private static final Pattern DIGITS_PATTERN = Pattern.compile("-?\\d+");

  private NumberUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  /**
   * Retrieves number part from a string containing both words and a number. If the sting contains more than one number, returns null.
   * <p>
   * Examples:
   * <ul>
   *   <li>4240 pixels -> 4240</li>
   *   <li>4240 -> 4240</li>
   *   <li>Width 4240 pixels -> 4240</li>
   * </ul>
   */
  public static Integer retrieveIntValue(String str) {
    if (StringUtils.isBlank(str)) {
      return null;
    }

    String trimmed = str.trim();
    Matcher matcher = DIGITS_PATTERN.matcher(trimmed);
    if (matcher.find()) {
      String match = matcher.group();
      if (matcher.find()) { // second call finds another group of digits → invalid
        return null;
      }

      return Integer.parseInt(match);
    }

    return null;
  }
}
