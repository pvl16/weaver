package ru.weaver.loomGUI;

import javax.swing.*;

public class GUISampleParameters extends GUIPatternParameters {

  public boolean isGet() {
    super.isGet();
    res = true;
    return true;
  }

  GUISampleParameters() {
    super();
    setTitle("New Sample Parameters");
    this.setSize(500, 300);
  }

}
