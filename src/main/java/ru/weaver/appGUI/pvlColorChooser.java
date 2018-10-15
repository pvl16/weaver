package ru.weaver.appGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class pvlColorChooser {
    private JColorChooser pane;
    private ColorTracker ok;
    private JDialog dialog;

    public pvlColorChooser() {
        pane = new JColorChooser(Color.white);
        javax.swing.colorchooser.AbstractColorChooserPanel panels[] = pane.getChooserPanels();
        for (int i = 0; i < panels.length; i++) {
            if ((i != 3) && (i != 0)) {
                pane.removeChooserPanel(panels[i]);
            }
        }
        pane.setPreviewPanel(new JPanel());
        ok = new ColorTracker(pane);
        dialog = (JDialog)JColorChooser.createDialog(null, "Choise color", true, pane, ok, null);
    }

    public Color getColor(String title, Color curColor) {
        dialog.setTitle(title);
        pane.setColor(curColor);
        dialog.show();
        return ok.color;
    }

    class ColorTracker implements ActionListener, Serializable {
        JColorChooser chooser;
        Color color;

        public ColorTracker(JColorChooser c) {
            chooser = c;
        }

        public void actionPerformed(ActionEvent e) {
            color = chooser.getColor();
        }

        public Color getColor() {
            return color;
        }
    }

}
