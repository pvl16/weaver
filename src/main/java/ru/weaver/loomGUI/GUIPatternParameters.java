package ru.weaver.loomGUI;

import javax.swing.*;

public class GUIPatternParameters extends JDialog {
    protected boolean res = false;
//    static JFrame paramFrame = null;
//
//    public static abstract void init() {
//        paramFrame = new JFrame();
//    }
//
//    public static void init() {
//    }
//
//    public static boolean isGetParameters() {
//        return false;
//    }

    public boolean isGet() {
        res = false;
        this.setVisible(true);
        return res;
    }

    public GUIPatternParameters() {
        super();
        this.setModal(true);
    }

}
