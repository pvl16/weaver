package ru.weaver.appGUI;

import ru.weaver.loomGUI.GUIPattern;

import javax.swing.*;
import java.util.ArrayList;

public class GUIUtils {
  private static JDesktopPane mainDescFrame;
  private static ArrayList<JLabel> statLb;

  public static void init(JDesktopPane ADesktopPane) {
    mainDescFrame = ADesktopPane;
    statLb = new ArrayList<JLabel>();
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

  public static GUIPattern selected() {
    JInternalFrame t = mainDescFrame.getSelectedFrame();
    if (t instanceof GUIPattern) {
      return ((GUIPattern)t);
    }
    return null;
  }

  public static void AddStatLb(JLabel lb) {
    statLb.add(lb);
  }

  public static void SetStatText(int Index, String Text) {
    if ((Index < 0) || (Index >= statLb.size())) return;
    statLb.get(Index).setText(Text);
  }

}
