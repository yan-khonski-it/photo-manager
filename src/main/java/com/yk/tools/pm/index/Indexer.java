package com.yk.tools.pm.index;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Builds index for given index entry.
 */
public class Indexer {

  private static final Logger LOGGER = LogManager.getLogger(Indexer.class);

  public void buildIndex(IndexProperties indexProperties) {
    String directory = indexProperties.getDirectory();
    File directoryFile = new File(directory);

    if (directoryFile.isFile()) {
      LOGGER.error("Directory is not a file");
      return;
    }

    if (!directoryFile.exists()) {
      boolean mkDirResult = directoryFile.mkdir();
      if (!mkDirResult) {
        LOGGER.error("Failed to create directory {}.", directory);
      }
    }

    File[] files = directoryFile.listFiles();
    if  (files == null) {
      LOGGER.info("Some error happened, no files.");
    } else {
      LOGGER.info("Indexing complete.");
    }
  }

}
