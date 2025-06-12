package com.yk.tools.pm.image;

import static org.assertj.core.api.Assertions.assertThat;

import com.yk.tools.pm.utils.TestUtils;
import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ImageMetadataExtractorTest {

  @Test
  void testExtractMetadataValidImage() {
    ImageMetadataExtractor extractor = new ImageMetadataExtractor();

    File imageFile1 = TestUtils.getFileFromResources("pictures/p1_20250404_153223.jpg");
    Map<String, String> actualMetadata1 = extractor.extractImageMetadata(imageFile1);
    Map<String, String> expectedMetadata1 = ImageMetadataSamples.getMetadataForP1();
    assertThat(actualMetadata1).isEqualTo(expectedMetadata1);

    File imageFile2 = TestUtils.getFileFromResources("pictures/example.jpg");
    Map<String, String> actualMetadata2 = extractor.extractImageMetadata(imageFile2);
    Map<String, String> expectedMetadata2 = ImageMetadataSamples.getMetadataForExample();
    assertThat(actualMetadata2).isEqualTo(expectedMetadata2);

    File imageFile3 = TestUtils.getFileFromResources("pictures/random.png");
    Map<String, String> actualMetadata3 = extractor.extractImageMetadata(imageFile3);
    Map<String, String> expectedMetadata3 = ImageMetadataSamples.getMetadataForRandom();
    assertThat(actualMetadata3).isEqualTo(expectedMetadata3);

    File imageFile4 = TestUtils.getFileFromResources("pictures/random1.png");
    Map<String, String> actualMetadata4 = extractor.extractImageMetadata(imageFile4);
    Map<String, String> expectedMetadata4 = ImageMetadataSamples.getMetadataForRandom1();
    assertThat(actualMetadata4).isEqualTo(expectedMetadata4);

    File imageFile5 = TestUtils.getFileFromResources("pictures/random2.png");
    Map<String, String> actualMetadata5 = extractor.extractImageMetadata(imageFile5);
    Map<String, String> expectedMetadata5 = ImageMetadataSamples.getMetadataForRandom2();
    assertThat(actualMetadata5).isEqualTo(expectedMetadata5);
  }

  @Test
  void testExtractMetadataInvalidFileThrowsException() {
    ImageMetadataExtractor extractor = new ImageMetadataExtractor();

    File nonExistientFile = new File("src/test/resources/invalid.txt");
    Map<String, String> metadata1 = extractor.extractImageMetadata(nonExistientFile);
    assertThat(metadata1).isEmpty();

    File invalidPicture1 = TestUtils.getFileFromResources("pictures/invalidPicture1.jpg");
    Map<String, String> metadata2 = extractor.extractImageMetadata(invalidPicture1);
    assertThat(metadata2).isEmpty();

    File invalidPicture2 = TestUtils.getFileFromResources("pictures/random2-corrupted.png");
    Map<String, String> metadata3 = extractor.extractImageMetadata(invalidPicture2);
    assertThat(metadata3).isEmpty();
  }
}