package com.yk.tools.pm.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.yk.tools.pm.entity.ImageIndexEntry;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Reads image file and its metadata
 */
public class ImageReader {

  private static final Logger LOGGER = LogManager.getLogger(ImageReader.class);

  public ImageIndexEntry readImage(File file) {
    try {
      BufferedImage image = ImageIO.read(file);
      LOGGER.debug(image);
    } catch (IOException e) {
      LOGGER.warn("Failed to read an image. File: {}.", file.getAbsolutePath(), e);
      return null;
    }

    extractMetadata(file);
    return null;
  }

  private Object extractMetadata(File file) {
    Metadata metadata = null;
    try {
      metadata = ImageMetadataReader.readMetadata(file);
    } catch (IOException e) {
      LOGGER.warn("Failed to read an image. File: {}.", file.getAbsolutePath(), e);
    } catch (ImageProcessingException e) {
      LOGGER.warn("Failed to read an image metadata. File: {}.", file.getAbsolutePath(), e);
    }

    if (metadata == null) {
      return null;
    }

    Map<String, String> retrievedTags = new HashMap<>();

    for (Directory directory : metadata.getDirectories()) {
      for (Tag tag : directory.getTags()) {
        retrievedTags.put(tag.getTagName(), tag.getDescription());
      }
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
