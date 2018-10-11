package ru.weaver.appGUI;

import ru.weaver.loomGUI.GUISample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuListener implements ActionListener {
  private Container frm;

  public MainMenuListener(Container AFrame) {
    super();
    frm = AFrame;
  }

  public void actionPerformed(ActionEvent event) {
    {
      String s = event.getActionCommand();
      if (s.equalsIgnoreCase("NewSample")) {
        GUISample gs = new GUISample();
        frm.add(gs);
        gs.setVisible(true);
      }
    }
  }
}
