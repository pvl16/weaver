package ru.weaver.appGUI;

import javax.swing.*;

public class pvlJButton extends JButton {
    pvlJButton(Action action, boolean isTool) {
        super(action);
        if (isTool) {
            this.setText("");
        }
        if (action instanceof pvlAbstractAction) {
            pvlAbstractAction ac = (pvlAbstractAction)action;
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

    public pvlJButton(Action action) {
        new pvlJButton(action, false);
        }
}
