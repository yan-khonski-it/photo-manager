package com.yk.tools.pm.utils;

import static java.lang.String.format;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class ImageUtils {

  private ImageUtils() {
    throw new AssertionError("Instantiation not allowed.");
  }

  public static byte[] writeImmageToByteArray(BufferedImage image, String format) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try {
      ImageIO.write(image,  "JPEG", baos);
    } catch (IOException e) {
      throw new IllegalStateException(format("Failed to write an image into byte array. Format: %s.", format), e);
    }

    return baos.toByteArray();
  }
}
