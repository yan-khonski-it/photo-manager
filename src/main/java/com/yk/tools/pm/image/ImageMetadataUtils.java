package com.yk.tools.pm.image;

import java.time.LocalDateTime;
import java.util.Map;

public final class ImageMetadataUtils {

  private ImageMetadataUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static LocalDateTime retrieveDateTime(Map<String, String> metadata) {
    String datatime = metadata.get("Date/Time");
    if (datatime == null) {
      datatime = metadata.get("Date/Time Digitized");
    }

    for (Map.Entry<String, String> entry : metadata.entrySet()) {
      String key = entry.getKey();
      if (key.contains("date") || key.contains("Date")) {
        datatime = entry.getValue();
      }
    }

    if (datatime == null) {
      return null;
    }

    return null;
  }

}
