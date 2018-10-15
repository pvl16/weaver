package ru.weaver.appGUI;

import javax.swing.*;
import java.net.URL;

public abstract class pvlAbstractAction extends AbstractAction {
    private String toolTipText;
    private KeyStroke keyStroke;
    private ImageIcon icon;

    public pvlAbstractAction(String name, String toolTip) {
        super(name);
        this.toolTipText = toolTip;
        this.keyStroke = null;
        this.icon = null;
    }

    public pvlAbstractAction(String name, String toolTip, String kStroke) {
        super(name);
        this.toolTipText = toolTip;
        if ((kStroke != null) && (!kStroke.equals(""))) {
            try {
                this.keyStroke = KeyStroke.getKeyStroke(kStroke);
            } catch (Exception e) {
            }
        }
        this.icon = null;
    }

    public pvlAbstractAction(String name, String toolTip, String kStroke, String iconName) {
        super(name);
        this.toolTipText = toolTip;
        if ((kStroke != null) && (!kStroke.equals(""))) {
            try {
                this.keyStroke = KeyStroke.getKeyStroke(kStroke);
            } catch (Exception e) {
            }
        }
        this.icon = null;
        {
            ClassLoader cl = this.getClass().getClassLoader();
            URL urlicon = cl.getResource("icon/" + iconName);
            if (!(urlicon == null)) {
                this.icon = new ImageIcon(urlicon);
            }
        }
    }

    public String getToolTipText() {
        return toolTipText;
    }

//    public void setToolTipText(String toolTipText) {
//        this.toolTipText = toolTipText;
//    }

    public KeyStroke getKeyStroke() {
        return keyStroke;
    }

//    public void setKeyStroke(KeyStroke keyStroke) {
//        this.keyStroke = keyStroke;
//    }

    public ImageIcon getIcon() {
        return icon;
    }

//    public void setIcon(ImageIcon icon) {
//        this.icon = icon;
//    }

}
