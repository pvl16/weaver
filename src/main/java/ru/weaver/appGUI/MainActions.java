package ru.weaver.appGUI;

import ru.weaver.NotCreatePattern;
import ru.weaver.loomGUI.GUIPattern;
import ru.weaver.loomGUI.GUISample;
import ru.weaver.loomGUI.GUITabled;

import javax.swing.*;
import java.awt.event.ActionEvent;

class MainActions {
    static Action exit;
    static Action newSample;
    static Action newTabled;
    static Action saveFile;
    static Action zoomInSample;
    static Action zoomOutSample;
    static Action zoom10Sample;
    static Action sampleTopLeft;
    static Action sampleTopRight;
    static Action sampleBtmLeft;
    static Action sampleBtmRight;

    static void init() {
        exit            = new Exit();
        newSample       = new NewSample();
        newTabled       = new NewTabled();
        saveFile        = new SaveFile();
        zoomInSample    = new ZoomInSample();
        zoomOutSample   = new ZoomOutSample();
        zoom10Sample    = new Zoom10Sample();
        sampleTopLeft   = new SampleTopLeft();
        sampleTopRight  = new SampleTopRight();
        sampleBtmLeft   = new SampleBtmLeft();
        sampleBtmRight  = new SampleBtmRight();
    }

    private static class Exit extends pvlAbstractAction {

        public Exit() {
            super("Exit", "Exit from application", "alt X", "");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    private static class NewSample extends pvlAbstractAction {

        public NewSample() {
            super("New Sample", "Create sample pattern", "ctrl alt C", "newsample.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUISample gs = new GUISample();
                GUIUtils.addFrame(gs);
                gs.setVisible(true);
                gs.getComponentPopupMenu().show(gs, 5, 5);
            } catch (NotCreatePattern e) {
            }
        }
    }

    private static class NewTabled extends pvlAbstractAction {

        public NewTabled() {
            super("New Sample", "Create tabled pattern", "ctrl alt T", "newtabled.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUITabled gt = new GUITabled();
                GUIUtils.addFrame(gt);
                gt.setVisible(true);
                gt.getComponentPopupMenu().show(gt, 5, 5);
            } catch (NotCreatePattern e) {
            }
        }
    }

    private static class SaveFile extends pvlAbstractAction {

        public SaveFile() {
            super("Save file", "Save pattern to file", "ctrl F", "save.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUIPattern a = GUIUtils.selected();
                if (a == null) return;
                a.SaveFile();
            } catch (Exception e) {
            }
        }
    }

    private static class ZoomInSample extends pvlAbstractAction {

        public ZoomInSample() {
            super("Zoom in", "Zoom in pattern", "ctrl + +", "zoom_in.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUIPattern a = GUIUtils.selected();
                if (a == null) return;
                a.zoomIn();
            } catch (Exception e) {
            }
        }
    }

    private static class ZoomOutSample extends pvlAbstractAction {

        public ZoomOutSample() {
            super("Zoom out", "Zoom out pattern sample", "ctrl + -", "zoom_out.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUIPattern a = GUIUtils.selected();
                if (a == null) return;
                a.zoomOut();
            } catch (Exception e) {
            }
        }
    }

    private static class Zoom10Sample extends pvlAbstractAction {

        public Zoom10Sample() {
            super("", "Zoom to 10 pattern sample", "ctrl 0", "zoom_10.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUIPattern a = GUIUtils.selected();
                if (a == null) return;
                a.zoom10();
            } catch (Exception e) {
            }
        }
    }

    private static class SampleTopLeft extends pvlAbstractAction {
        public SampleTopLeft() {
            super("...top-left", "Sample in top-left corner", "", "pos_tl.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUIPattern a = GUIUtils.selected();
                if (a == null) return;
                if (a instanceof GUISample) {
                    ((GUISample)a).posTopLeft();
                }
            } catch (Exception e) {
            }
        }
    }

    private static class SampleTopRight extends pvlAbstractAction {

        public SampleTopRight() {
            super("...top-right", "Sample in top-right corner", "", "pos_tr.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUIPattern a = GUIUtils.selected();
                if (a == null) return;
                if (a instanceof GUISample) {
                    ((GUISample)a).posTopRight();
                }
            } catch (Exception e) {
            }
        }
    }

    private static class SampleBtmLeft extends pvlAbstractAction {

        public SampleBtmLeft() {
            super("...bottom-left", "Sample in bottom-left corner", "", "pos_bl.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUIPattern a = GUIUtils.selected();
                if (a == null) return;
                if (a instanceof GUISample) {
                    ((GUISample)a).posBtmLeft();
                }
            } catch (Exception e) {
            }
        }
    }

    private static class SampleBtmRight extends pvlAbstractAction {

        public SampleBtmRight() {
            super("...bottom-right", "Sample in bottom-right corner", "", "pos_br.png");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                GUIPattern a = GUIUtils.selected();
                if (a == null) return;
                if (a instanceof GUISample) {
                    ((GUISample)a).posBtmRight();
                }
            } catch (Exception e) {
            }
        }
    }

}
