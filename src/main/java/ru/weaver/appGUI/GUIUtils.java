package ru.weaver.appGUI;

import javax.swing.*;

public class GUIUtils {
  private static JDesktopPane mainDescFrame;

  public static void init(JDesktopPane ADesktopPane) {
    mainDescFrame = ADesktopPane;
  }

  public static JDesktopPane getMainDescFrame() {
    return mainDescFrame;
  }

  public static void addFrame(JInternalFrame jInternalFrame) {
    mainDescFrame.add(jInternalFrame);
//    jInternalFrame.setBounds(25, 25, 200, 100);
    jInternalFrame.setSize(500, 300);
    jInternalFrame.setVisible(true);
  }

}
