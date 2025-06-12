package com.yk.tools.pm.image;

import com.yk.tools.pm.entity.ImageIndexEntry;
import com.yk.tools.pm.image.metadata.DateTimeExtractor;
import com.yk.tools.pm.image.metadata.ImageMetadataExtractor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Reads image file and its metadata
 */
public class ImageReader {

  private static final Logger LOGGER = LogManager.getLogger(ImageReader.class);

  private final ImageMetadataExtractor metadataExtractor;

  public ImageReader() {
    metadataExtractor = new ImageMetadataExtractor();
  }

  public ImageIndexEntry readImage(File file) {
    try {
      BufferedImage image = ImageIO.read(file);
      LOGGER.debug(image);
    } catch (IOException e) {
      LOGGER.warn("Failed to read an image. File: {}.", file.getAbsolutePath(), e);
      return null;
    }

    Map<String, String> metadata = metadataExtractor.extractImageMetadata(file);
    LocalDateTime createdDateTime = DateTimeExtractor.extractDateTime(metadata);
    if (createdDateTime == null) {
      LOGGER.warn("Could not extract date time from metadata for file: {}.", file.getAbsolutePath());
    } else {
      LOGGER.info("Created image at {}.", createdDateTime);
    }

    /*
    String make = retrievedTags.get("Make");
    String model = retrievedTags.get("Model");
    String dataTime = retrievedTags.get("Date/Time");
    String height = retrievedTags.get("Image Height");
    String width = retrievedTags.get("Image Width");
*/

    return null;
  }
}
