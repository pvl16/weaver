package ru.weaver.appGUI;

import ru.weaver.NotCreatePattern;
import ru.weaver.loomGUI.GUIPattern;
import ru.weaver.loomGUI.GUISample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class MainMenuListener implements ActionListener {

  public MainMenuListener() {
    super();
  }

  public void actionPerformed(ActionEvent event) {
    {
      try {
        Class[] paramTypes = new Class[]{};
        Method m = this.getClass().getMethod(event.getActionCommand(), paramTypes);
        if (m != null) {
          Object[] argv = new Object[]{};
          m.invoke(this, argv);
        }
      } catch (Exception e) {
//        e.printStackTrace();
      }
    }
  }

  public void NewSample() {
    try {
      GUISample gs = new GUISample();
      GUIUtils.addFrame(gs);
      gs.setVisible(true);
      gs.getComponentPopupMenu().show(gs, 5, 5);
    } catch (NotCreatePattern e) {
    }
  }

  public void Exit() { System.exit(0); }

  public void ZoomInSample() {
    GUIPattern a = GUIUtils.selected();
    if (a == null) return;
    a.zoomIn();
  }

  public void ZoomOutSample() {
    GUIPattern a = GUIUtils.selected();
    if (a == null) return;
    a.zoomOut();
  }

  public void Zoom10Sample() {
    GUIPattern a = GUIUtils.selected();
    if (a == null) return;
    a.zoom10();
  }

  public void SampleTopLeft() {
      GUIPattern a = GUIUtils.selected();
      if (a == null) return;
      if (a instanceof GUISample) {
          ((GUISample)a).posTopLeft();
      }
  }

  public void SampleTopRight() {
      GUIPattern a = GUIUtils.selected();
      if (a == null) return;
      if (a instanceof GUISample) {
          ((GUISample)a).posTopRight();
      }
  }

  public void SampleBtmLeft() {
      GUIPattern a = GUIUtils.selected();
      if (a == null) return;
      if (a instanceof GUISample) {
          ((GUISample)a).posBtmLeft();
      }
  }

  public void SampleBtmRight() {
      GUIPattern a = GUIUtils.selected();
      if (a == null) return;
      if (a instanceof GUISample) {
          ((GUISample)a).posBtmRight();
      }
  }

}
