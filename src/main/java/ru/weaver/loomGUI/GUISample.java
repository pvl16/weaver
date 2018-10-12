package ru.weaver.loomGUI;

import ru.weaver.NotCreatePattern;
import ru.weaver.loom.Sample;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static ru.weaver.loomGUI.GUISample.PosSample.TOPLEFT;

public class GUISample extends GUIPattern {
    enum PosSample{TOPLEFT, TOPRIGHT, BTMLEFT, BTMRIGHT};
    private Sample sample;
    private short zoom;
    private PosSample posSample;

    private class Pans extends JPanel {
        int szW;
        int szH;
        int posW;
        int posH;

        protected int getWCount() {
            int res = 0;
            if ((this instanceof PanView)||(this instanceof PanWarps)) {
                res = sample.getCntWarps();
            } else if ((this instanceof PanWefts)||(this instanceof PanSample)) {
                res = sample.getCntTreadles();
            }
            return res;
        }

        protected int getHCount() {
            int res = 0;
            if ((this instanceof PanView)||(this instanceof PanWefts)) {
                res = sample.getCntWefts();
            } else if ((this instanceof PanWarps)||(this instanceof PanSample)) {
                res = sample.getCntHeddles();
            }
            return res;
        }

        protected int getAWCount() {
            return sample.getCntWarps() + sample.getCntTreadles();
        }

        protected int getAHCount() {
            return sample.getCntWefts() + sample.getCntHeddles();
        }

        public void onSetSize() {
            int wcnt = this.getWCount();
            int awcnt = this.getAWCount();
            int hcnt = this.getHCount();
            int ahcnt = this.getAHCount();
            szW = (wcnt + 2) * zoom;
            szH = (hcnt + 2) * zoom;
            if (this instanceof PanView) {
                switch (posSample) {
                    case TOPLEFT:
                        posW = 0;
                        posH = 0;
                        break;
                    case TOPRIGHT:
                        posW = (awcnt + 2) * zoom - szW;
                        posH = 0;
                        break;
                    case BTMLEFT:
                        posW = 0;
                        posH = (ahcnt + 2) * zoom - szH;
                        break;
                    case BTMRIGHT:
                        posW = (awcnt + 2) * zoom - szW;
                        posH = (ahcnt + 2) * zoom - szH;
                        break;
                }
            }
        }

        public Pans() {
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            this.onSetSize();
        }
    }

    private class PanView extends Pans {
    }

    private class PanWarps extends Pans {
    }

    private class PanWefts extends Pans {
    }

    private class PanSample extends Pans {
    }

    private class SampleLayout implements LayoutManager {

//        @Override
//        public void addLayoutComponent(String name, Component comp) {
//        }
//
//        @Override
//        public void removeLayoutComponent(Component comp) {
//        }
//
//        @Override
//        public Dimension preferredLayoutSize(Container parent) {
//            return null;
//        }
//
//        @Override
//        public Dimension minimumLayoutSize(Container parent) {
//            return null;
//        }

        @Override
        public void addLayoutComponent(String name, Component comp) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return null;
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return null;
        }

        @Override
        public void layoutContainer(Container parent) {
            for (Component comp: parent.getComponents()) {
                if (comp instanceof Pans) {
                    Pans p = (Pans)comp;
                    comp.setBounds(p.posW, p.posH, p.szW, p.szH);
                }
            }
        }
    }

    private PanView panView;
    private PanWarps panWarps;
    private PanWefts panWefts;
    private PanSample panSample;

    protected void initParams() {
        parameters = new GUISampleParameters();
    }

    public GUISample() throws NotCreatePattern {
        super();

        zoom = 10;
        posSample = PosSample.BTMRIGHT;

        sample = new Sample(4, 4, 50, 20, true, Color.BLACK, Color.WHITE);
        setTitle("New Sample");
        jPatPanel.setLayout(new SampleLayout());
        panView = new PanView();
        panView.setSize(400, 400);
        panWefts = new PanWefts();
        panWefts.setSize(100, 400);
        panWarps = new PanWarps();
        panWarps.setSize(400, 100);
        panSample = new PanSample();
        panSample.setSize(100, 100);
//        GridBagConstraints constraints = new GridBagConstraints();
        jPatPanel.add(panView);
        jPatPanel.add(panWefts);
        jPatPanel.add(panWarps);
        jPatPanel.add(panSample);
    }


}
