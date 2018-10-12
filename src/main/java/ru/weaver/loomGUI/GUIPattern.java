package ru.weaver.loomGUI;

import javax.swing.*;
import java.awt.*;

public class GUIPattern extends JInternalFrame {
  protected JPanel jPanel;
  protected JScrollPane scrollPane;

  public GUIPattern() {
    super();
    this.setSize(500, 300);
    this.setResizable(true);
    this.setClosable(true);
    this.setMaximizable(true);
    this.setIconifiable(true);
    jPanel = new JPanel();
//    scrollPane = new JScrollPane(this);
    scrollPane = new JScrollPane(jPanel);

    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    this.getContentPane().setLayout(gridbag);
    c.gridx = 0;
    c.gridy = 0;
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1.0;
    c.weighty = 1.0;
    gridbag.setConstraints(scrollPane, c);
//cont.setLayout(new BorderLayout());
    this.getContentPane().add(scrollPane);
  }

}
