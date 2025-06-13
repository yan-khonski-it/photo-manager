package com.yk.tools.pm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NumberUtils {

  private static final Pattern DIGITS_PATTERN = Pattern.compile("-?\\d+");

  private NumberUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static Integer retrieveIntValue(String str) {
    if (StringUtils.isBlank(str)) {
      return null;
    }

    String trimmed = str.trim();
    Matcher matcher = DIGITS_PATTERN.matcher(trimmed);
    Integer value = null;

    int count = 0;
    while (matcher.find()) {
      count++;
      // More than one match? Not valid for your case.
      if (count > 1) { // NOPMD - AvoidLiteralsInIfCondition
        return null;
      }
      value = Integer.parseInt(matcher.group());
    }
    return count == 1 ? value : null;
  }
}
