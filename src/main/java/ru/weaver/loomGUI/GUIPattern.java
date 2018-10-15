package ru.weaver.loomGUI;

import ru.weaver.NotCreatePattern;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIPattern extends JInternalFrame {
    protected JScrollPane scrollPane;
    protected JPanel jPatPanel;
    protected GUIPatternParameters parameters;
    protected JPopupMenu popMenu;
    protected short zoom;
    protected File filePath;
    protected JFileChooser fileChooser;

    protected void inits() {
        this.setSize(500, 300);
        this.setResizable(true);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);

        jPatPanel = new JPanel();
        scrollPane = new JScrollPane(jPatPanel);
        this.add(scrollPane);

        popMenu = new JPopupMenu("Pop-Up");
        jPatPanel.setComponentPopupMenu(popMenu);
        zoom = 10;

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new PatternFilter());

        initParams();
    }

    public GUIPattern() throws NotCreatePattern {
        inits();
        if (!getParams()) {
            throw new NotCreatePattern();
        }
        filePath = null;
    }

    public GUIPattern(File path) throws NotCreatePattern {
        inits();
        if (!LoadFile(path)) {
            throw new NotCreatePattern();
        }
        filePath = path;
    }

    protected void initParams() {
        parameters = new GUIPatternParameters();
    }

    protected boolean getParams() {
        return parameters.isGet();
    }

    public void onSetSize() {

    }

    public void zoomIn()  { if (zoom < 20) zoom++; onSetSize(); }
    public void zoomOut() { if (zoom >  4) zoom--; onSetSize(); }
    public void zoom10()  { zoom = 10; onSetSize(); }

    protected void AfterChooseFileSave() {

    }

    public void SaveFile() {
        if (filePath == null) {
            fileChooser.setDialogTitle("Сохранение файла");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);
            // Если файл выбран, то представим его в сообщении
            if (result == JFileChooser.APPROVE_OPTION ) {
                filePath = fileChooser.getSelectedFile();
                if (!filePath.toString().toLowerCase().endsWith(".wvr")) {
                    filePath = new File(filePath.toString() + ".wvr");
                }
            } else {
                return;
            }
        }
        AfterChooseFileSave();
    }

    public boolean LoadFile(File filePath) {
        return false;
    }

    private class PatternFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".txt" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".wvr");
        }

        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "Weaver pattern (*.wvr)";
        }
    }

}
