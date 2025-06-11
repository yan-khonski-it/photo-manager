package com.yk.tools.pm.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Extracts image metadata.
 */
public class ImageMetadataExtractor {

  private static final Logger LOGGER = LogManager.getLogger(ImageMetadataExtractor.class);

  public Map<String, String> extractImageMetadata(File file) {
    Optional<Metadata> metadata = readImageMetadata(file);
    return metadata.map(this::convertMetadataToMap).orElseGet(Map::of);

  }

  private Optional<Metadata> readImageMetadata(File file) {
    try {
      return Optional.ofNullable(ImageMetadataReader.readMetadata(file));
    } catch (IOException e) {
      LOGGER.warn("Failed to read an image. File: {}.", file.getAbsolutePath(), e);
    } catch (ImageProcessingException e) {
      LOGGER.warn("Failed to read image metadata. File: {}.", file.getAbsolutePath(), e);
    }

    return Optional.empty();
  }

  private Map<String, String> convertMetadataToMap(Metadata metadata) {
    Map<String, String> retrievedTags = new HashMap<>();
    for (Directory directory : metadata.getDirectories()) {
      for (Tag tag : directory.getTags()) {
        retrievedTags.put(tag.getTagName(), tag.getDescription());
      }
    }

    return Collections.unmodifiableMap(retrievedTags);
  }

}
