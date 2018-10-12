package ru.weaver.appGUI;

import ru.weaver.loomGUI.GUIPatternParameters;

import javax.swing.*;
import java.awt.event.KeyEvent;

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
      {
        JMenuItem open_pattern = new JMenuItem("Open Pattern");
        open_pattern.setActionCommand("OpenPattern");
        open_pattern.addActionListener(new MainMenuListener());
        fileMenu.add(open_pattern);
      }
      {
        JMenuItem close_pattern = new JMenuItem("Close Pattern", KeyEvent.VK_L);
        close_pattern.setActionCommand("ClosePattern");
        close_pattern.addActionListener(new MainMenuListener());
        fileMenu.add(close_pattern);
      }
      fileMenu.add(new JSeparator());
      {
        JMenuItem exit_item = new JMenuItem("Exit", KeyEvent.VK_N);
        exit_item.setActionCommand("Exit");
        exit_item.addActionListener(new MainMenuListener());
        KeyStroke altXKeyStroke = KeyStroke.getKeyStroke("alt X");
        exit_item.setAccelerator(altXKeyStroke);
        fileMenu.add(exit_item);
      }
    }
//    fileMenu = new JMenu("File");
//    helpMenu = new JMenu("Help");
//    menuBar.add(fileMenu);
//    menuBar.add(helpMenu);

  }

}
