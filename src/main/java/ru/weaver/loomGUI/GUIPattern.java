package ru.weaver.loomGUI;

import ru.weaver.NotCreatePattern;

import javax.swing.*;
import java.awt.*;

public class GUIPattern extends JInternalFrame {
//  protected JPanel jPanel;
  protected JScrollPane scrollPane;
  protected JPanel jPatPanel;
  protected GUIPatternParameters parameters;
  protected JPopupMenu popMenu;
  protected short zoom;

  public GUIPattern() throws NotCreatePattern {
    initParams();
    if (!getParams()) {
      throw new NotCreatePattern();
    }
    this.setSize(500, 300);
    this.setResizable(true);
    this.setClosable(true);
    this.setMaximizable(true);
    this.setIconifiable(true);

    jPatPanel = new JPanel();
    scrollPane = new JScrollPane(jPatPanel);
    this.add(scrollPane);

    popMenu = new JPopupMenu("Pop-Up");
    jPatPanel.setComponentPopupMenu(popMenu);
    zoom = 10;
  }

  protected void initParams() {
    parameters = new GUIPatternParameters();
  }

  protected boolean getParams() {
    return parameters.isGet();
  }

  public void onSetSize() {

  }

  public void zoomIn()  { if (zoom < 20) zoom++; onSetSize(); }
  public void zoomOut() { if (zoom >  4) zoom--; onSetSize(); }
  public void zoom10()  { zoom = 10; onSetSize(); }

}
