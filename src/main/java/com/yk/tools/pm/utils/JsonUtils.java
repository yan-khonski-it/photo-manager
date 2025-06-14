package com.yk.tools.pm.utils;

import static java.lang.String.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public final class JsonUtils {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private JsonUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static String toJson(Object object) {
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (IOException e) {
      throw new IllegalStateException(
          format("Failed to serialize object into JSON. Class: %s, Object: %s", object.getClass().getSimpleName(), object), e);
    }
  }
}
