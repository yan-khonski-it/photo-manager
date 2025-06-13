package com.yk.tools.pm.image.metadata;

import com.yk.tools.pm.utils.NumberUtils;
import java.util.Map;

public final class OtherParametersMetadataExtractor {

  private OtherParametersMetadataExtractor() {
    throw new AssertionError("Instance is not allowed.");
  }

  /**
   * Extracts the value for 'Model' or 'model' from metadata.
   */
  public static String extractModel(Map<String, String> metadata) {
    if (metadata.containsKey("Model")) {
      return metadata.get("Model");
    } else if (metadata.containsKey("model")) {
      return metadata.get("model");
    }
    return null;
  }

  /**
   * Extracts the value for 'Make' or 'make' from metadata.
   */
  public static String extractMake(Map<String, String> metadata) {
    if (metadata.containsKey("Make")) {
      return metadata.get("Make");
    } else if (metadata.containsKey("make")) {
      return metadata.get("make");
    }
    return null;
  }

  /**
   * Extracts the width from metadata, checking common keys and parsing as int.
   */
  public static Integer extractWidth(Map<String, String> metadata) {
    String[] widthKeys = {"Width", "Image Width", "Exif Image Width"};
    for (String key : widthKeys) {
      String value = metadata.get(key);
      if (value != null) {
        return NumberUtils.retrieveIntValue(value);
      }
    }
    return null;
  }

  /**
   * Extracts the height from metadata, checking common keys and parsing as int.
   */
  public static Integer extractHeight(Map<String, String> metadata) {
    String[] heightKeys = {"Height", "Image Height", "Exif Image Height"};
    for (String key : heightKeys) {
      String value = metadata.get(key);
      if (value != null) {
        return NumberUtils.retrieveIntValue(value);
      }
    }
    return null;
  }


}