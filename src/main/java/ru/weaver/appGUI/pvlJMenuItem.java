package ru.weaver.appGUI;

import javax.swing.*;

class pvlJMenuItem extends JMenuItem {
    pvlJMenuItem(Action action) {
        super(action);
        if (action instanceof pvlAbstractAction) {
            pvlAbstractAction ac = (pvlAbstractAction)action;
            {
                KeyStroke ks = ac.getKeyStroke();
                if (ks != null) {
                    this.setAccelerator(ks);
                }
            }
            {
                String tt = ac.getToolTipText();
                if ((!(tt == null))&&(!tt.equals(""))) {
                    this.setToolTipText(tt);
                }
            }
            {
                ImageIcon ic = ac.getIcon();
                if (!(ic == null)) {
                    this.setIcon(ic);
                }
            }
        }
    }

    pvlJMenuItem(String name) {
        super(name);
    }
}
