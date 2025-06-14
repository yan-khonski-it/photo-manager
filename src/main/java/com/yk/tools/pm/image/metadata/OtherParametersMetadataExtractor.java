package com.yk.tools.pm.image.metadata;

import com.yk.tools.pm.utils.NumberUtils;
import java.util.Map;
import java.util.function.Function;

public final class OtherParametersMetadataExtractor {

  private static final String MODEL = "Model";
  private static final String MAKE = "Make";
  private static final String FILE_SIZE = "File Size";

  private static final String[] WIDTH_KEYS = {"Image Width", "Exif Image Width", "Width"};
  private static final String[] HEIGHT_KEYS = {"Image Height", "Exif Image Height", "Height"};


  private OtherParametersMetadataExtractor() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static String extractModel(Map<String, String> metadata) {
    return metadata.get(MODEL);
  }

  public static String extractMake(Map<String, String> metadata) {
    return metadata.get(MAKE);
  }

  /**
   * Extracts the width from metadata, checking common keys and parsing as int.
   */
  public static Integer extractWidth(Map<String, String> metadata) {
    return extractValueByKeysAndConvert(metadata, WIDTH_KEYS, NumberUtils::retrieveIntValue);
  }

  /**
   * Extracts the height from metadata, checking common keys and parsing as int.
   */
  public static Integer extractHeight(Map<String, String> metadata) {
    return extractValueByKeysAndConvert(metadata, HEIGHT_KEYS, NumberUtils::retrieveIntValue);
  }

  public static Long extractSize(Map<String, String> metadata) {
    return extractValueByKeyAndConvert(metadata, FILE_SIZE, NumberUtils::retrieveLongValue);
  }

  /**
   * Given metadata and array of keys, extract first non null value from metadata by one of the keys. If extracted value is not null, apply converter function
   * to convert it to the final result.
   * <p>
   * It is useful to extract value that can be linked to several keys, for example, {@code "Width", "Image Width", "Exif Image Width"}, then if value is not
   * null, it can be converted to other type, such as {@code Long}.
   */
  private static <T> T extractValueByKeysAndConvert(Map<String, String> metadata, String[] keys, Function<String, T> converter) {
    for (String key : keys) {
      if (metadata.containsKey(key)) {
        String value = metadata.get(key);
        if (value != null) {
          return converter.apply(value);
        } else {
          return null;
        }
      }
    }

    return null;
  }

  private static <T> T extractValueByKeyAndConvert(Map<String, String> metadata, String key, Function<String, T> converter) {
    if (metadata.containsKey(key)) {
      String value = metadata.get(key);
      if (value != null) {
        return converter.apply(value);
      } else {
        return null;
      }
    }

    return null;
  }
}