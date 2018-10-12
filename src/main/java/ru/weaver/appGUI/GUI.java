package ru.weaver.appGUI;

import ru.weaver.loomGUI.GUIPatternParameters;

import javax.swing.*;

public class GUI {
  private JFrame mainFrame = null;
  private JDesktopPane descFrame = null;
  private JMenuBar menuBar = null;

  public GUI() {
    mainFrame = new JFrame("Weaver");
    descFrame = new JDesktopPane();
    mainFrame.getContentPane().add(descFrame);
    menuBar = new JMenuBar();
    mainFrame.setJMenuBar(menuBar);
    buildMenu();

    GUIUtils.init(descFrame);
//    GUIPatternParameters.init();
  }

  public void init() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {

    }
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mainFrame.pack();
    mainFrame.setVisible(true);
    mainFrame.setExtendedState(mainFrame.MAXIMIZED_BOTH);
    descFrame.setVisible(true);

//    mainFrame.setLocationRelativeTo(null);
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
          newSample.addActionListener(new MainMenuListener());
          newMenu.add(newSample);
        }
        {
          JMenuItem newTabled = new JMenuItem("New Tabled");
          newTabled.addActionListener(new MainMenuListener());
          newMenu.add(newTabled);
        }
        fileMenu.add(newMenu);
      }
    }
//    fileMenu = new JMenu("File");
//    helpMenu = new JMenu("Help");
//    menuBar.add(fileMenu);
//    menuBar.add(helpMenu);

  }

}
