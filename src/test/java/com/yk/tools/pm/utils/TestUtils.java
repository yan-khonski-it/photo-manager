package com.yk.tools.pm.utils;

import static java.lang.String.format;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Utils used in tests
 */
public final class TestUtils {

  private TestUtils() {
    throw new AssertionError("Instantiation is not allowed.");
  }

  public static File getFileFromResources(String fileName) {
    ClassLoader classLoader = TestUtils.class.getClassLoader();
    if (classLoader == null) {
      throw new RuntimeException(format("Failed to get classloader for %s.", TestUtils.class.getSimpleName()));
    }

    URL url = classLoader.getResource(fileName);
    if (url == null) {
      throw new RuntimeException(format("Resource: %s not found.", fileName));
    }

    String urlFile = url.getFile();
    if (urlFile.isBlank()) {
      throw new RuntimeException(format("Resource: %s not found. ", fileName));
    }

    try {
      return Paths.get(url.toURI()).toFile();
    } catch (URISyntaxException e) {
      throw new RuntimeException(format("Invalid URI for resource '%s'.", fileName), e);
    }
  }
}
