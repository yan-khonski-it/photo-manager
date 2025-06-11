package com.yk.tools.pm.index;

public class IndexProperties {

  private final String directory;
  private final String name;

  public IndexProperties(String directory, String name) {
    this.directory = directory;
    this.name = name;
  }

  public String getDirectory() {
    return directory;
  }

  public String getName() {
    return name;
  }
}
