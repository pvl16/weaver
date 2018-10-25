package ru.weaver.appGUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class GUI {
    private JFrame mainFrame;
    private JDesktopPane descFrame;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JPanel statBar;

    public GUI() {
        mainFrame = new JFrame("Weaver");
        descFrame = new JDesktopPane();
        GUIUtils.init(descFrame);
        MainActions.init();
        mainFrame.getContentPane().add(descFrame);
        menuBar = new JMenuBar();
        toolBar = new JToolBar();
        statBar = new JPanel();
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(toolBar, BorderLayout.PAGE_START);
        mainFrame.add(statBar, BorderLayout.PAGE_END);
        buildMenu();
        buildTools();
        buildStatBar();
    }

    public void init() {
        try {
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
                    pvlJMenuItem newSample = new pvlJMenuItem(MainActions.newSample);
                    newMenu.add(newSample);
                }
                {
                    pvlJMenuItem newTabled = new pvlJMenuItem(MainActions.newTabled);
                    newMenu.add(newTabled);
                }
                fileMenu.add(newMenu);
            }
            {
                pvlJMenuItem open_pattern = new pvlJMenuItem("Open Pattern");
                fileMenu.add(open_pattern);
            }
            {
                pvlJMenuItem close_pattern = new pvlJMenuItem("Close Pattern");
                fileMenu.add(close_pattern);
            }
            fileMenu.add(new JSeparator());
            {
                pvlJMenuItem saveFile = new pvlJMenuItem(MainActions.saveFile);
                fileMenu.add(saveFile);
            }
            fileMenu.add(new JSeparator());
            {
                pvlJMenuItem exit_item = new pvlJMenuItem(MainActions.exit);
                fileMenu.add(exit_item);
            }
        }
        {
            JMenu viewMenu = new JMenu("View");
            menuBar.add(viewMenu);
            {
                JMenu zoomMenu = new JMenu("Zoom");
                viewMenu.add(zoomMenu);
                {
                    pvlJMenuItem zoominSample = new pvlJMenuItem(MainActions.zoomInSample);
                    zoomMenu.add(zoominSample);
                }
                {
                    pvlJMenuItem zoomoutSample = new pvlJMenuItem(MainActions.zoomOutSample);
                    zoomMenu.add(zoomoutSample);
                }
                {
                    pvlJMenuItem zoom10Sample = new pvlJMenuItem(MainActions.zoom10Sample);
                    zoomMenu.add(zoom10Sample);
                }
            }
            {
                JMenu sinMenu = new JMenu("Sample in...");
                viewMenu.add(sinMenu);
                {
                    pvlJMenuItem s = new pvlJMenuItem(MainActions.sampleTopLeft);
                    sinMenu.add(s);
                }
                {
                    pvlJMenuItem s = new pvlJMenuItem(MainActions.sampleTopRight);
                    sinMenu.add(s);
                }
                {
                    pvlJMenuItem s = new pvlJMenuItem(MainActions.sampleBtmLeft);
                    sinMenu.add(s);
                }
                {
                    pvlJMenuItem s = new pvlJMenuItem(MainActions.sampleBtmRight);
                    sinMenu.add(s);
                }
            }
        }
    }

    private void buildTools() {
        ClassLoader cl = this.getClass().getClassLoader();
        toolBar.setFloatable(false);
        {
            pvlJButton bt = new pvlJButton(MainActions.newSample, true);
            toolBar.add(bt);
        }
        {
            pvlJButton bt = new pvlJButton(MainActions.newTabled, true);
            toolBar.add(bt);
        }
        {
            pvlJButton bt = new pvlJButton(MainActions.saveFile, true);
            toolBar.add(bt);
        }
        toolBar.addSeparator();
        {
            pvlJButton bt = new pvlJButton(MainActions.zoomInSample, true);
            toolBar.add(bt);
        }
        {
            pvlJButton bt = new pvlJButton(MainActions.zoomOutSample, true);
            toolBar.add(bt);
        }
        {
            pvlJButton bt = new pvlJButton(MainActions.zoom10Sample, true);
            toolBar.add(bt);
        }
        toolBar.addSeparator();
        {
            pvlJButton bt = new pvlJButton(MainActions.sampleTopLeft, true);
            toolBar.add(bt);
        }
        {
            pvlJButton bt = new pvlJButton(MainActions.sampleTopRight, true);
            toolBar.add(bt);
        }
        {
            pvlJButton bt = new pvlJButton(MainActions.sampleBtmLeft, true);
            toolBar.add(bt);
        }
        {
            pvlJButton bt = new pvlJButton(MainActions.sampleBtmRight, true);
            toolBar.add(bt);
        }
        toolBar.addSeparator();
    }

    private void buildStatBar() {
        statBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        for (int i = 0; i< 5; i++) {
//          JPanel p = new JPanel();
//          p.setBorder(new BevelBorder(BevelBorder.LOWERED));
//          statBar.add(p, BorderLayout.PAGE_START);
            JLabel l = new JLabel("");
            l.setHorizontalAlignment(SwingConstants.LEFT);
            l.setBorder(new BevelBorder(BevelBorder.LOWERED));
            GUIUtils.AddStatLb(l);
            statBar.add(l);
        }
    }

}
