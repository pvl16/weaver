package ru.weaver.loom;

public class Pattern {
  protected String name;
//  protected M

  public Pattern() {
    name = "Pattern 1";
  }

  protected void forSaveFile_init() {

  }

  protected void forSaveFile_getPattern() {

  }

  public void savetoFile(String path) {
    forSaveFile_init();
    forSaveFile_getPattern();

  }

}
