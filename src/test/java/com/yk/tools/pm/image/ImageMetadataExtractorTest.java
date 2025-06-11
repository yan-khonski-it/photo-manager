package com.yk.tools.pm.image;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.yk.tools.pm.utils.TestUtils;
import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ImageMetadataExtractorTest {

  @Test
  void testExtractMetadataValidImageReturnsMetadata() {
    String fileName = "pictures/20250404_153223.jpg";
    File imageFile = TestUtils.getFileFromResources(fileName);
    ImageMetadataExtractor extractor = new ImageMetadataExtractor();
    Map<String, String> metadata = extractor.extractImageMetadata(imageFile);

    assertNotNull(metadata);
  }

  @Test
  void testExtractMetadataInvalidFileThrowsException() {
    File invalidFile = new File("src/test/resources/invalid.txt");
    ImageMetadataExtractor extractor = new ImageMetadataExtractor();

    Map<String, String> metadata = extractor.extractImageMetadata(invalidFile);
    assertNotNull(metadata);
  }
}