package com.yk.tools.pm.image.metadata;

import static org.assertj.core.api.Assertions.assertThat;

import com.yk.tools.pm.utils.TestUtils;
import java.io.File;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ImageMetadataExtractorTest {

  private static Stream<Arguments> imageFilesAndExpectedMetadata() {
    return Stream.of(
        Arguments.of("pictures/p1_20250404_153223.jpg", ImageMetadataSamples.getMetadataForP1()),
        Arguments.of("pictures/p2_20250522_134927.jpg", ImageMetadataSamples.getMetadataForP2()),
        Arguments.of("pictures/random.png", ImageMetadataSamples.getMetadataForRandom()),
        Arguments.of("pictures/random1.png", ImageMetadataSamples.getMetadataForRandom1()),
        Arguments.of("pictures/random2.png", ImageMetadataSamples.getMetadataForRandom2()),
        Arguments.of("pictures/example.jpg", ImageMetadataSamples.getMetadataForExample()),
        Arguments.of("pictures/viber-1.jpg", ImageMetadataSamples.getMetadataForViber1()),
        Arguments.of("pictures/ZV-E1-1.JPG", SonyZV_E1_MetadataSample.getSonyZVE1Metadata())
    );
  }

  @ParameterizedTest
  @MethodSource("imageFilesAndExpectedMetadata")
  void testExtractMetadataValidImage(String filePath, Map<String, String> expectedMetadata) {
    ImageMetadataExtractor extractor = new ImageMetadataExtractor();
    File imageFile = TestUtils.getFileFromResources(filePath);
    Map<String, String> actualMetadata = extractor.extractImageMetadata(imageFile);
    assertThat(actualMetadata).isEqualTo(expectedMetadata);
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