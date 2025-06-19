package com.yk.tools.pm.utils;


public final class ArrayUtils {

  private ArrayUtils() {
    throw new AssertionError("Insance is not allowed.");
  }

  public static byte[] convertIntPixelsToBytes(int[] pixels) {
    byte[] rawBytes = new byte[pixels.length * 4];
    int idx = 0;

    for (int pixel : pixels) {
      rawBytes[idx++] = (byte) ((pixel >> 24) & 0xFF); // alpha
      rawBytes[idx++] = (byte) ((pixel >> 16) & 0xFF); // red
      rawBytes[idx++] = (byte) ((pixel >> 8) & 0xFF);  // green
      rawBytes[idx++] = (byte) (pixel & 0xFF);         // blue
    }

    return rawBytes;
  }

}
