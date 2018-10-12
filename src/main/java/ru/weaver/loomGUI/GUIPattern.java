package ru.weaver.loomGUI;

import ru.weaver.NotCreatePattern;

import javax.swing.*;
import java.awt.*;

public class GUIPattern extends JInternalFrame {
//  protected JPanel jPanel;
  protected JScrollPane scrollPane;
  protected JPanel jPatPanel;
  protected GUIPatternParameters parameters;

  protected void initParams() {
    parameters = new GUIPatternParameters();
  }

  protected boolean getParams() {
    return parameters.isGet();
  }

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
//    jPanel = new JPanel();
//    this.getContentPane().add(jPanel);
//    scrollPane = new JScrollPane(jPanel);
//    scrollPane.add(jPatPanel);
    jPatPanel = new JPanel();
    scrollPane = new JScrollPane(jPatPanel);
    this.add(scrollPane);

//    GridBagLayout gridbag = new GridBagLayout();
//    GridBagConstraints c = new GridBagConstraints();
//    this.getContentPane().setLayout(gridbag);
//    c.gridx = 0;
//    c.gridy = 0;
//    c.fill = GridBagConstraints.BOTH;
//    c.weightx = 1.0;
//    c.weighty = 1.0;
//    gridbag.setConstraints(scrollPane, c);
////cont.setLayout(new BorderLayout());
//    this.getContentPane().add(scrollPane);
  }

}
