package com.yk.tools.pm.image.metadata;

import com.yk.tools.pm.utils.ArrayUtils;
import com.yk.tools.pm.utils.HashUtils;
import com.yk.tools.pm.utils.ImageUtils;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.security.MessageDigest;

public final class ImageHashExtractor {

  private ImageHashExtractor() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static String computeImagePixelHash(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
    byte[] imageBytes = ImageUtils.writeImmageToByteArray(image, "JPEG");

    ArrayUtils.compareArrays(ArrayUtils.intArrayToByteArray(pixels), imageBytes);
    MessageDigest digest = HashUtils.messageDigestSha256();

    for (int pixel : pixels) {
      digest.update((byte) (pixel >> 24));  // alpha
      digest.update((byte) (pixel >> 16));  // red
      digest.update((byte) (pixel >> 8));   // green
      digest.update((byte) pixel);          // blue
    }

    return convertByteHashToHexHash(digest.digest());
  }

  private static String convertByteHashToHexHash(byte[] hashBytes) {
    // Convert the byte array to a hexadecimal string
    BigInteger bigInt = new BigInteger(1, hashBytes);
    StringBuilder hexHash = new StringBuilder(bigInt.toString(16));

    // Pad with leading zeros if necessary
    while (hexHash.length() < 64) { // SHA-256 hash is 64 hex characters long
      hexHash.insert(0, "0");
    }

    return hexHash.toString();
  }
}
