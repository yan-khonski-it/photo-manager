package com.yk.tools.pm.image;

import java.util.HashMap;
import java.util.Map;

/**
 * This util class provides real data about photo metadata for tests assertions. Now we clearly see what the real metadata looks like.
 */
public final class ImageMetadataSamples {

  private ImageMetadataSamples() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static Map<String, String> getMetadataForP1() {
    Map<String, String> metadata = new HashMap<>();

    metadata.put("Time Zone", "+02:00");
    metadata.put("Compression", "JPEG (old-style)");
    metadata.put("Number of Tables", "4 Huffman tables");
    metadata.put("Exif Version", "2.20");
    metadata.put("Compression Type", "Baseline");
    metadata.put("Number of Components", "3");
    metadata.put("Component 2", "Cb component: Quantization table 1, Sampling factors 1 horiz/1 vert");
    metadata.put("Focal Length", "6.4 mm");
    metadata.put("Component 1", "Y component: Quantization table 0, Sampling factors 2 horiz/2 vert");
    metadata.put("YCbCr Positioning", "Center of pixel array");
    metadata.put("Sub-Sec Time Digitized", "385");
    metadata.put("Date/Time Original", "2025:04:04 15:32:23");
    metadata.put("Shutter Speed Value", "1 sec");
    metadata.put("X Resolution", "72 dots per inch");
    metadata.put("Brightness Value", "10.1");
    metadata.put("Component 3", "Cr component: Quantization table 1, Sampling factors 1 horiz/1 vert");
    metadata.put("Unique Image ID", "DA8XLOD01SM");
    metadata.put("F-Number", "f/1.8");
    metadata.put("Focal Length 35", "23 mm");
    metadata.put("Exposure Program", "Program normal");
    metadata.put("Digital Zoom Ratio", "1");
    metadata.put("Detected File Type Long Name", "Joint Photographic Experts Group");
    metadata.put("Exposure Time", "1/1528 sec");
    metadata.put("Detected MIME Type", "image/jpeg");
    metadata.put("Expected File Name Extension", "jpg");
    metadata.put("ISO Speed Ratings", "50");
    metadata.put("Make", "samsung");
    metadata.put("Orientation", "Top, left side (Horizontal / normal)");
    metadata.put("Metering Mode", "Center weighted average");
    metadata.put("Time Zone Original", "+02:00");
    metadata.put("Thumbnail Offset", "856 bytes");
    metadata.put("Software", "S908BXXSDEYB1");
    metadata.put("Exif Image Height", "2252 pixels");
    metadata.put("Y Resolution", "72 dots per inch");
    metadata.put("FlashPix Version", "1.00");
    metadata.put("Data Precision", "8 bits");
    metadata.put("Sub-Sec Time Original", "385");
    metadata.put("Thumbnail Length", "41656 bytes");
    metadata.put("Color Space", "sRGB");
    metadata.put("File Size", "3267049 bytes");
    metadata.put("Date/Time Digitized", "2025:04:04 15:32:23");
    metadata.put("File Name", "p1_20250404_153223.jpg");
    metadata.put("Flash", "Flash did not fire");
    metadata.put("Aperture Value", "f/1.8");
    metadata.put("File Modified Date", "Fri Apr 04 15:32:23 +02:00 2025");
    metadata.put("Date/Time", "2025:04:04 15:32:23");
    metadata.put("Exif Image Width", "4000 pixels");
    metadata.put("Image Height", "288 pixels");
    metadata.put("Image Width", "512 pixels");
    metadata.put("Resolution Unit", "Inch");
    metadata.put("Exposure Bias Value", "0 EV");
    metadata.put("Detected File Type Name", "JPEG");
    metadata.put("Max Aperture Value", "f/1.8");
    metadata.put("Exposure Mode", "Auto exposure");
    metadata.put("Model", "SM-S908B");
    metadata.put("Scene Capture Type", "Standard");
    metadata.put("Sub-Sec Time", "385");
    metadata.put("White Balance Mode", "Auto white balance");

    return metadata;
  }

  public static Map<String, String> getMetadataForExample() {
    Map<String, String> metadata = new HashMap<>();

    metadata.put("Resolution Units", "inch");
    metadata.put("Number of Tables", "4 Huffman tables");
    metadata.put("Detected File Type Long Name", "Joint Photographic Experts Group");
    metadata.put("File Modified Date", "Fri Jun 13 00:32:15 +02:00 2025");
    metadata.put("Compression Type", "Baseline");
    metadata.put("Data Precision", "8 bits");
    metadata.put("Detected MIME Type", "image/jpeg");
    metadata.put("Expected File Name Extension", "jpg");
    metadata.put("Number of Components", "3");
    metadata.put("Component 2", "Cb component: Quantization table 1, Sampling factors 1 horiz/1 vert");
    metadata.put("Thumbnail Height Pixels", "0");
    metadata.put("Component 1", "Y component: Quantization table 0, Sampling factors 2 horiz/2 vert");
    metadata.put("Image Height", "267 pixels");
    metadata.put("Thumbnail Width Pixels", "0");
    metadata.put("X Resolution", "300 dots");
    metadata.put("Image Width", "400 pixels");
    metadata.put("File Size", "61136 bytes");
    metadata.put("Component 3", "Cr component: Quantization table 1, Sampling factors 1 horiz/1 vert");
    metadata.put("Version", "1.1");
    metadata.put("Detected File Type Name", "JPEG");
    metadata.put("File Name", "example.jpg");
    metadata.put("Y Resolution", "300 dots");

    return metadata;
  }

  public static Map<String, String> getMetadataForRandom() {
    Map<String, String> metadata = new HashMap<>();

    metadata.put("Filter Method", "Adaptive");
    metadata.put("Detected File Type Long Name", "Portable Network Graphics");
    metadata.put("File Modified Date", "Fri Jun 13 00:38:16 +02:00 2025");
    metadata.put("Compression Type", "Deflate");
    metadata.put("Detected MIME Type", "image/png");
    metadata.put("Expected File Name Extension", "png");
    metadata.put("Image Height", "512");
    metadata.put("Image Width", "512");
    metadata.put("File Size", "3415 bytes");
    metadata.put("Interlace Method", "No Interlace");
    metadata.put("Detected File Type Name", "PNG");
    metadata.put("File Name", "random.png");
    metadata.put("Color Type", "True Color");
    metadata.put("Bits Per Sample", "8");

    return metadata;
  }

  public static Map<String, String> getMetadataForRandom1() {
    Map<String, String> metadata = new HashMap<>();

    metadata.put("Filter Method", "Adaptive");
    metadata.put("Detected File Type Long Name", "Portable Network Graphics");
    metadata.put("File Modified Date", "Fri Jun 13 00:44:59 +02:00 2025");
    metadata.put("Compression Type", "Deflate");
    metadata.put("Detected MIME Type", "image/png");
    metadata.put("Expected File Name Extension", "png");
    metadata.put("Image Height", "512");
    metadata.put("Image Width", "512");
    metadata.put("File Size", "19058 bytes");
    metadata.put("Interlace Method", "No Interlace");
    metadata.put("Detected File Type Name", "PNG");
    metadata.put("File Name", "random1.png");
    metadata.put("Color Type", "True Color");
    metadata.put("Bits Per Sample", "8");

    return metadata;
  }

  public static Map<String, String> getMetadataForRandom2() {
    Map<String, String> metadata = new HashMap<>();

    metadata.put("Filter Method", "Adaptive");
    metadata.put("Detected File Type Long Name", "Portable Network Graphics");
    metadata.put("File Modified Date", "Fri Jun 13 00:47:00 +02:00 2025");
    metadata.put("Compression Type", "Deflate");
    metadata.put("Detected MIME Type", "image/png");
    metadata.put("Expected File Name Extension", "png");
    metadata.put("Image Height", "512");
    metadata.put("Image Width", "512");
    metadata.put("File Size", "5386 bytes");
    metadata.put("Interlace Method", "No Interlace");
    metadata.put("Detected File Type Name", "PNG");
    metadata.put("File Name", "random2.png");
    metadata.put("Color Type", "True Color");
    metadata.put("Bits Per Sample", "8");

    return metadata;
  }
}
