package com.yk.tools.pm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

  private static final Logger LOGGER = LogManager.getLogger(App.class);

  public void execute() {
    LOGGER.info("Running...");
  }
}
