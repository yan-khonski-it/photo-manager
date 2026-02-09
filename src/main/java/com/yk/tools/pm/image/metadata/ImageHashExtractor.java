package com.yk.tools.pm.image.metadata;

import com.yk.tools.pm.utils.ArrayUtils;
import com.yk.tools.pm.utils.HashUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ImageHashExtractor {

  private static final Logger LOGGER = LogManager.getLogger(ImageHashExtractor.class);

  @SuppressWarnings("PMD.LongVariable")
  private static final int MINIMUM_IMAGE_SIZE = 4096; // 64*64, We will ignore smaller images.

  private ImageHashExtractor() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static String computeImagePixelHash(File file) {
    BufferedImage image;

    try {
      image = ImageIO.read(file);
    } catch (IOException e) {
      LOGGER.warn("Failed to read an image. File: {}.", file.getAbsolutePath(), e);
      return null;
    }

    int width = image.getWidth();
    int height = image.getHeight();
    if (!validateImageDimensions(file, width, height)) {
      return null;
    }

    int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
    if (pixels.length < MINIMUM_IMAGE_SIZE) {
      // TODO Fix it
      LOGGER.warn("Fix it");
    }

    byte[] pixelBytes = ArrayUtils.convertIntPixelsToBytes(pixels);

    MessageDigest messageDigest = HashUtils.messageDigestSha256();
    byte[] hashBytes = messageDigest.digest(pixelBytes);
    return convertByteHashToHexHash(hashBytes);
  }

  private static String convertByteHashToHexHash(byte[] hashBytes) {
    StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
    for (byte b : hashBytes) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }

    return hexString.toString();
  }

  private static boolean validateImageDimensions(File file, int width, int height) {
    if (width < MINIMUM_IMAGE_SIZE || height < MINIMUM_IMAGE_SIZE) {
      LOGGER.warn("Invalid image file: [%{}] dimensions. Width: {}, Height: {}.", file.getAbsolutePath(), width, height);
      return false;
    }

    return true;
  }
}
