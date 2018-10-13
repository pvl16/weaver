package ru.weaver.appGUI;

import ru.weaver.loomGUI.GUIPatternParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GUI {
  private JFrame mainFrame = null;
  private JDesktopPane descFrame = null;
  private JMenuBar menuBar = null;
  private JToolBar toolBar = null;
  private MainMenuListener listener = null;

  public GUI() {
    mainFrame = new JFrame("Weaver");
    descFrame = new JDesktopPane();
    mainFrame.getContentPane().add(descFrame);
    menuBar = new JMenuBar();
    toolBar = new JToolBar();
    mainFrame.setJMenuBar(menuBar);
    mainFrame.add(toolBar, BorderLayout.PAGE_START);
    listener = new MainMenuListener();
    buildMenu();
    buildTools();

    GUIUtils.init(descFrame);
//    GUIPatternParameters.init();
  }

  public void init() {
    try {
//      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
    } catch (Exception e) {

    }
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mainFrame.pack();
    mainFrame.setVisible(true);
    mainFrame.setExtendedState(mainFrame.MAXIMIZED_BOTH);
    descFrame.setVisible(true);

  }

  private void buildMenu() {
    {
      JMenu fileMenu = new JMenu("File");
      menuBar.add(fileMenu);
      {
        JMenu newMenu = new JMenu("New Pattern");
        {
          JMenuItem newSample = new JMenuItem("New Sample");
          newSample.setActionCommand("NewSample");
          newSample.setAccelerator(KeyStroke.getKeyStroke("ctrl alt C"));
          newSample.addActionListener(listener);
          newMenu.add(newSample);
        }
        {
          JMenuItem newTabled = new JMenuItem("New Tabled");
          newTabled.addActionListener(listener);
          newMenu.add(newTabled);
        }
        fileMenu.add(newMenu);
      }
      {
        JMenuItem open_pattern = new JMenuItem("Open Pattern");
        open_pattern.setActionCommand("OpenPattern");
        open_pattern.addActionListener(listener);
        fileMenu.add(open_pattern);
      }
      {
        JMenuItem close_pattern = new JMenuItem("Close Pattern", KeyEvent.VK_L);
        close_pattern.setActionCommand("ClosePattern");
        close_pattern.addActionListener(listener);
        fileMenu.add(close_pattern);
      }
      fileMenu.add(new JSeparator());
      {
        JMenuItem exit_item = new JMenuItem("Exit");
        exit_item.setActionCommand("Exit");
        exit_item.addActionListener(listener);
        exit_item.setAccelerator(KeyStroke.getKeyStroke("alt X"));
        fileMenu.add(exit_item);
      }
    }

  }

  private void buildTools() {
    toolBar.setFloatable(false);
    {
      JButton bt = new JButton();
      bt.setActionCommand("NewSample");
      bt.addActionListener(listener);
      toolBar.add(bt);
    }
    toolBar.addSeparator();
    {
      JButton bt = new JButton("+");
      bt.setActionCommand("ZoomInSample");
      bt.addActionListener(listener);
      toolBar.add(bt);
    }
    {
      JButton bt = new JButton("-");
      bt.setActionCommand("ZoomOutSample");
      bt.addActionListener(listener);
      toolBar.add(bt);
    }
    {
      JButton bt = new JButton("10");
      bt.setActionCommand("Zoom10Sample");
      bt.addActionListener(listener);
      toolBar.add(bt);
    }
    toolBar.addSeparator();
  }
}
