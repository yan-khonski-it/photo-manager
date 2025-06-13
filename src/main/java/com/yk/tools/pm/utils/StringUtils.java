package com.yk.tools.pm.utils;

public final class StringUtils {

  private StringUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static boolean isBlank(String str) {
    return str == null || str.isBlank();
  }
}
