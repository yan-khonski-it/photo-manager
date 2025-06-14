package com.yk.tools.pm;

import com.yk.tools.pm.entity.ImageIndexEntry;
import com.yk.tools.pm.image.ImageReader;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

  private static final Logger LOGGER = LogManager.getLogger(App.class);

  public void execute() {
    LOGGER.info("Running...");
    ImageReader imageReader = new ImageReader();

    String image1 = "C:\\Dev\\workspaces\\tools\\photo-manager\\src\\test\\resources\\pictures\\ZV-E1-1.JPG";
    ImageIndexEntry imageIndexEntry1 = imageReader.readImage(new File(image1));
    LOGGER.info(imageIndexEntry1);
  }
}
