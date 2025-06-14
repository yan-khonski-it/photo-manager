package com.yk.tools.pm.image;

import com.yk.tools.pm.entity.ImageIndexEntry;
import com.yk.tools.pm.image.metadata.DateTimeExtractor;
import com.yk.tools.pm.image.metadata.ImageHashExtractor;
import com.yk.tools.pm.image.metadata.ImageMetadataExtractor;
import com.yk.tools.pm.image.metadata.OtherParametersMetadataExtractor;
import com.yk.tools.pm.utils.JsonUtils;
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

  @SuppressWarnings("PMD.UnusedAssignment")
  public ImageIndexEntry readImage(File file) {
    String byteHashHex = null;

    try {
      BufferedImage image = ImageIO.read(file);
      byteHashHex = ImageHashExtractor.computeImagePixelHash(image);
      LOGGER.debug("Hash: {}", byteHashHex);
    } catch (IOException e) {
      LOGGER.warn("Failed to read an image. File: {}.", file.getAbsolutePath(), e);
      return null;
    }

    Map<String, String> metadata = metadataExtractor.extractImageMetadata(file);

    long fileSize = file.length();
    Long metadataSize = OtherParametersMetadataExtractor.extractSize(metadata);
    if (metadataSize != null && metadataSize != fileSize) {
      LOGGER.warn("Metadata size: {} is not equal to file size: {}. File: {}.", metadataSize, fileSize, file.getAbsolutePath());
    }

    String metadataJson = JsonUtils.toJson(metadata);

    LocalDateTime createdDateTime = DateTimeExtractor.extractDateTime(metadata);
    String make = OtherParametersMetadataExtractor.extractMake(metadata);
    String model = OtherParametersMetadataExtractor.extractModel(metadata);
    Integer width = OtherParametersMetadataExtractor.extractWidth(metadata);
    Integer height = OtherParametersMetadataExtractor.extractHeight(metadata);

    return new ImageIndexEntry(null, file.getName(), file.getParentFile().getAbsolutePath(), fileSize, width, height, byteHashHex, createdDateTime, make, model,
        metadataJson);
  }
}
