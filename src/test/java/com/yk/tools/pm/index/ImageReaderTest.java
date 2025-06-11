package com.yk.tools.pm.index;

import com.yk.tools.pm.image.ImageReader;
import java.io.File;
import org.junit.jupiter.api.Test;

class ImageReaderTest {

  @Test
  public void testReadImage() {
    String imagePath = "B:\\photo\\2016-07-13 Minsk Fedor Yan cycling\\P60713-205355.jpg";
    File imageFile = new File(imagePath);

    ImageReader imageReader = new ImageReader();
    imageReader.readImage(imageFile);
  }

}