package ru.weaver.appGUI;

import ru.weaver.NotCreatePattern;
import ru.weaver.loomGUI.GUISample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuListener implements ActionListener {

  public MainMenuListener() {
    super();
  }

  private void newSamle() {
    GUISample gs;
    try {
      gs = new GUISample();
    } catch (NotCreatePattern e) {
      return;
    }
    GUIUtils.addFrame(gs);
    gs.setVisible(true);
  }

  public void actionPerformed(ActionEvent event) {
    {
      String s = event.getActionCommand();
      if (s.equalsIgnoreCase("NewSample"))
        newSamle();
      if (s.equalsIgnoreCase("Exit"))
        System.exit(0);
    }
  }
}
