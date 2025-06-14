package com.yk.tools.pm.utils;

import static java.lang.String.format;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtils {

  private static final String HASH_ALGORITHM = "SHA-256";

  private HashUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  /**
   * MessageDigest - is not thread safe. Create a new instance each time.
   */
  public static MessageDigest messageDigestSha256() {
    try {
      return MessageDigest.getInstance(HASH_ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(format("We expected that algorithm: %s is valid.", HASH_ALGORITHM), e);
    }
  }
}
