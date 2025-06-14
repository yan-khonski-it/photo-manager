package com.yk.tools.pm.utils;

/**
 * Used for debugging.
 */
public final class ArrayUtils {

  private ArrayUtils() {
    throw new AssertionError("Insance is not allowed.");
  }

  public static String arrayToString(byte[] array) {
    StringBuilder sb = new StringBuilder();
    for (byte b : array) {
      sb.append(String.format("%02x", b));
    }

    return sb.toString();
  }


  public static int compareArrays(byte[] array1, byte[] array2) {
    int length1 = array1.length;
    int length2 = array2.length;
    int minLength = Math.min(length1, length2);

    for (int i = 0; i < minLength; i++) {
      if (array1[i] != array2[i]) {
        return array1[i] - array2[i];
      }
    }

    return length1 == length2 ? 0 : length1 - length2;
  }

  public static byte[] intArrayToByteArray(int[] ints) {
    byte[] bytes = new byte[ints.length * 4];
    for (int i = 0; i < ints.length; i++) {
      int value = ints[i];
      int base = i * 4;
      bytes[base]     = (byte) (value >> 24);
      bytes[base + 1] = (byte) (value >> 16);
      bytes[base + 2] = (byte) (value >> 8);
      bytes[base + 3] = (byte) value;
    }
    return bytes;
  }
}
