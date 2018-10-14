package ru.weaver.loomGUI;

import ru.weaver.NotCreatePattern;
import ru.weaver.loom.Sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;

import ru.weaver.appGUI.GUIUtils;

import static ru.weaver.loomGUI.GUISample.PosSample.*;

public class GUISample extends GUIPattern {
    enum PosSample{TOPLEFT, TOPRIGHT, BTMLEFT, BTMRIGHT};
    private Sample sample;
    private int tZoom;
    private PosSample posSample;
//    private SampleLayout sampleLayout;

    public GUISample() throws NotCreatePattern {
        super();

        onSetZoom();
        posSample = PosSample.BTMLEFT;
//        sampleLayout = new SampleLayout();

        sample = new Sample(4, 4, 50, 20, true, Color.GREEN, Color.RED);
        setTitle("New Sample");
//        jPatPanel.setLayout(sampleLayout);
        jPatPanel.setLayout(null);
        panView = new PanView();
//        panView.setComponentPopupMenu(popMenu);
        panWefts = new PanWefts();
//        panWefts.setComponentPopupMenu(popMenu);
        panWarps = new PanWarps();
//        panWarps.setComponentPopupMenu(popMenu);
        panSample = new PanSample();
//        panSample.setComponentPopupMenu(popMenu);
        jPatPanel.add(panView);
        jPatPanel.add(panWefts);
        jPatPanel.add(panWarps);
        jPatPanel.add(panSample);
        makePop();
//        MouseListener popupListener = new PopupListener();
//        this.pack();
        onSetSize();
    }

    private void onSetZoom() { tZoom = 4 + 3 * zoom; }
    public void onSetSize() {
        onSetZoom();
        panView.onSetSize();
        panWefts.onSetSize();
        panWarps.onSetSize();
        panSample.onSetSize();
        Dimension d = new Dimension((getAXCount() + 2) * tZoom, (getAYCount() + 2) * tZoom);
        jPatPanel.setPreferredSize(d);
        jPatPanel.setMaximumSize(d);
        scrollPane.repaint();
//        this.doLayout();
//        this.pack();
//        this.repaint();
    }

    private int getAXCount() { return sample.getCntWarps() + sample.getCntTreadles(); }
    private int getAYCount() { return sample.getCntWefts() + sample.getCntHeddles();  }

    private boolean isSampleTop()  { return ((posSample == TOPLEFT)||(posSample == TOPRIGHT)); }
    private boolean isSampleLeft() { return ((posSample == TOPLEFT)||(posSample == BTMLEFT));  }

    private class Pans extends JPanel implements MouseListener {
        int szX;
        int szY;
        int posX;
        int posY;
        int kXCnt;
        int kYCnt;
        int kXPos;
        int kYPos;
        int bgX;
        int bgY;
        int dX;
        int dY;

        public Pans() {
            this.setKXY();
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            this.onSetPosSample();
            this.onSetSize();
//            this.addMouseListener(new PopupListener());
        }

        protected int getXCount() {
            return kXCnt * sample.getCntWarps() + (1 - kXCnt) * (sample.getCntTreadles() + 2);
        }

        protected int getYCount() {
            return kYCnt * sample.getCntWefts() + (1 - kYCnt) * (sample.getCntHeddles() + 2);
        }

        protected void setKXY() { kXCnt = 0; kYCnt = 0; }

        protected void onSetPosSample() {
            kXPos = 0;
            kYPos = 0;
        }

        public void onSetSize() {
            int xcnt  = getXCount();
            int axcnt = getAXCount();
            int ycnt  = getYCount();
            int aycnt = getAYCount();
            szX = xcnt * tZoom;
            szY = ycnt * tZoom;

            posX = kXPos * ((axcnt - xcnt + 2) * tZoom + 1);
            posY = kYPos * ((aycnt - ycnt + 2) * tZoom + 1);

            onAfterSetSize();
            this.setSize(szX, szY);
            this.setLocation(posX, posY);
        }

        public void onAfterSetSize() {
            bgX = 0; dX = 0;
            bgY = 0; dY = 0;
        }

        protected void onClicked(int X, int Y) {
            return;
        }

        protected void onEntered(int X, int Y) {
            return;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            onClicked(e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            onEntered(e.getX(), e.getY());
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

//        private class PopupListener implements MouseListener {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                if (e.isPopupTrigger()) {
//                    popMenu.show(e.getComponent(), e.getX(), e.getY());
//                }
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                if (e.isPopupTrigger()) {
//                    popMenu.show(e.getComponent(), e.getX(), e.getY());
//                }
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        }
    }

    private class PanView extends Pans {

        protected void setKXY() { kXCnt = 1; kYCnt = 1; }

        protected void onSetPosSample() {
            kXPos = (isSampleLeft()) ? 1 : 0;
            kYPos = (isSampleTop())  ? 1 : 0;
        }

        public void onAfterSetSize() {
            if (!isSampleLeft()) { bgX = 1;               dX =  tZoom; }
            else                 { bgX = szX - tZoom - 1; dX = -tZoom; }
            if ( isSampleTop())  { bgY = 1;               dY =  tZoom; }
            else                 { bgY = szY - tZoom - 1; dY = -tZoom; }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cntX = sample.getCntWarps();
            int cntY = sample.getCntWefts();
            int cX = 0;
            int cY = bgY;

            for(int i = 0; i < cntY; i++) {
                cX = bgX;
                for (int j = 0; j < cntX ; j++) {
                    Color c = Color.WHITE;
                    try {
                        c = sample.getColor(j, i);
                    } catch (Exception e) {
                    }
                    g.setColor(c);
                    g.fillRect(cX, cY, tZoom, tZoom);
                    cX += dX;
                }
                cY += dY;
            }
        }

        protected void onClicked(int X, int Y) {

            return;
        }

    }

    private class PanWarps extends Pans {
        protected void setKXY() { kXCnt = 1; kYCnt = 0; }

        protected void onSetPosSample() {
            kXPos = (isSampleLeft()) ? 1 : 0;
            kYPos = (isSampleTop())  ? 0 : 1;
        }

        public void onAfterSetSize() {
            if ( isSampleLeft()) { bgX = 1;               dX =  tZoom; }
            else                 { bgX = szX - tZoom - 1; dX = -tZoom; }
            if (!isSampleTop())  { bgY = 1;               dY =  tZoom; }
            else                 { bgY = szY - tZoom - 1; dY = -tZoom; }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                int cntX = sample.getCntWarps();
                int cntY = sample.getCntHeddles();
                int cX = 0;
                int cY = bgY;

                for(int i = 0; i < cntY; i++) {
                    cX = bgX;
                    for (int j = 0; j < cntX ; j++) {
                        g.setColor(Color.WHITE);
                        g.fillRect(cX, cY, tZoom, tZoom);
                        g.setColor(Color.BLACK);
                        g.drawRect(cX, cY, tZoom, tZoom);
                        if (sample.getHeddleWarp(j) == i) {
                            g.fillRect(cX, cY, tZoom, tZoom);
                        }
                        cX += dX;
                    }
                    cY += dY;
                }
                cX = bgX;
                for (int i = 0; i < cntX; i++) {
                    g.setColor(Color.WHITE);
                    g.fillRect(cX, cY, tZoom, tZoom);
                    g.setColor(sample.getColorWarp(i));
                    g.fillRect(cX, cY, tZoom, tZoom);
                    g.setColor(Color.BLACK);
                    g.drawRect(cX, cY, tZoom, tZoom);
                    g.drawRect(cX, cY + dY, tZoom, tZoom);
                    g.drawString(String.format("%d", i + 1), cX, cY + dY);
                    cX += dX;
                }
            } catch (Exception e) {
            }

        }

        protected void onClicked(int X, int Y) {
            int nX = X / tZoom;
            int nY = Y / tZoom;
            return;
        }

        protected void onEntered(int X, int Y) {
            int nX = X / tZoom;
            int nY = Y / tZoom;
            if ((nX >= 0)&&(nX < sample.getCntWarps())) {
                GUIUtils.SetStatText(3, String.format("%d", nX));
            }

        }

    }

    private class PanWefts extends Pans {
        protected void setKXY() { kXCnt = 0; kYCnt = 1; }

        protected void onSetPosSample() {
            kXPos = (isSampleLeft()) ? 0 : 1;
            kYPos = (isSampleTop())  ? 1 : 0;
        }

        public void onAfterSetSize() {
            if (!isSampleLeft()) { bgX = 1;               dX =  tZoom; }
            else                 { bgX = szX - tZoom - 1; dX = -tZoom; }
            if ( isSampleTop())  { bgY = 1;               dY =  tZoom; }
            else                 { bgY = szY - tZoom - 1; dY = -tZoom; }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cntX = sample.getCntTreadles();
            int cntY = sample.getCntWefts();
            int cX = 0;
            int cY = bgY;

            for(int i = 0; i < cntY; i++) {
                cX = bgX;
                for (int j = 0; j < cntX ; j++) {
                    g.setColor(Color.WHITE);
                    g.fillRect(cX, cY, tZoom, tZoom);
                    g.setColor(Color.BLACK);
                    g.drawRect(cX, cY, tZoom, tZoom);
                    boolean isUp = false;
                    try {
                        isUp = (sample.getTreadleWeft(i) == j);
                    } catch (Exception e) {
                    }
                    if (isUp) {
                        g.fillRect(cX, cY, tZoom, tZoom);
                    }
                    cX += dX;
                }

                g.setColor(Color.WHITE);
                g.fillRect(cX, cY, tZoom, tZoom);
                g.fillRect(cX + dX, cY, tZoom, tZoom);
                g.setColor(sample.getColorWeft(i));
                g.fillRect(cX + 1, cY + 1, tZoom - 2, tZoom - 2);
                g.setColor(Color.BLACK);
                g.drawRect(cX, cY, tZoom, tZoom);
                g.drawRect(cX + dX, cY, tZoom, tZoom);
                g.drawString(String.format("%d", i + 1), cX + dX, cY);
                cX += dX;


                cY += dY;
            }
        }

    }

    private class PanSample extends Pans {
        protected void setKXY() { kXCnt = 0; kYCnt = 0; }

        protected void onSetPosSample() {
            kXPos = (isSampleLeft()) ? 0 : 1;
            kYPos = (isSampleTop())  ? 0 : 1;
        }

        public void onAfterSetSize() {
            if (!isSampleLeft()) { bgX = 1;               dX =  tZoom; }
            else                 { bgX = szX - tZoom - 1; dX = -tZoom; }
            if (!isSampleTop())  { bgY = 1;               dY =  tZoom; }
            else                 { bgY = szY - tZoom - 1; dY = -tZoom; }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int cntX = sample.getCntTreadles();
            int cntY = sample.getCntHeddles();
            int cX = 0;
            int cY = bgY;

            for(int i = 0; i < cntY; i++) {
                cX = bgX;
                for (int j = 0; j < cntX ; j++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(cX, cY, tZoom, tZoom);
                    boolean isUp = false;
                    try {
                        isUp = sample.getIsSampleUp(i, j);
                    } catch (Exception e) {
                    }
                    if (!isUp) {
                        g.setColor(Color.WHITE);
                        g.fillRect(cX + 1, cY + 1, tZoom -2, tZoom - 2);
                    }
                    cX += dX;
                }
                cY += dY;
            }
        }

    }

//    private class SampleLayout implements LayoutManager {
//
//        public void addLayoutComponent(String name, Component comp) {
//        }
//
//        public void removeLayoutComponent(Component comp) {
//        }
//
//        private Dimension calculateBestSize(Container c) {
//            return new Dimension((getAXCount() + 2) * tZoom, (getAYCount() + 2) * tZoom);
//        }
//
//        public Dimension preferredLayoutSize(Container parent) {
//            return calculateBestSize(parent);
//        }
//
//        public Dimension minimumLayoutSize(Container parent) {
//            return calculateBestSize(parent);
//        }
//
//        public void layoutContainer(Container parent) {
//            for (Component comp: parent.getComponents()) {
//                if (comp instanceof Pans) {
//                    Pans p = (Pans)comp;
//                    comp.setBounds(p.posX, p.posY, p.szX, p.szY);
//                }
//            }
//        }
//    }

    private PanView panView;
    private PanWarps panWarps;
    private PanWefts panWefts;
    private PanSample panSample;

    private void onSetPos() {
        panView.onSetPosSample();
        panWarps.onSetPosSample();
        panWefts.onSetPosSample();
        panSample.onSetPosSample();
    }

    protected void initParams() {
        parameters = new GUISampleParameters();
    }

    private void makePop() {
        {
            JMenu posMenu = new JMenu("Set sample position");
            popMenu.add(posMenu);
            {
                JMenuItem posTopLeft = new JMenuItem("Sample in Top-Left");
                posTopLeft.setActionCommand("posTopLeft");
                posTopLeft.setAccelerator(KeyStroke.getKeyStroke("ctrl alt 7"));
                posTopLeft.addActionListener(new PopMenuListener());
                posTopLeft.add(posMenu);
            }
            {
                JMenuItem posTopLeft = new JMenuItem("Sample in Top-Right");
                posTopLeft.setActionCommand("posTopRight");
                posTopLeft.setAccelerator(KeyStroke.getKeyStroke("ctrl alt 9"));
                posTopLeft.addActionListener(new PopMenuListener());
                posTopLeft.add(posMenu);
            }
            {
                JMenuItem posTopLeft = new JMenuItem("Sample in Bottom-Left");
                posTopLeft.setActionCommand("posBtmLeft");
                posTopLeft.setAccelerator(KeyStroke.getKeyStroke("ctrl alt 1"));
                posTopLeft.addActionListener(new PopMenuListener());
                posTopLeft.add(posMenu);
            }
            {
                JMenuItem posTopLeft = new JMenuItem("Sample in Bottom-Right");
                posTopLeft.setActionCommand("posBtmRight");
                posTopLeft.setAccelerator(KeyStroke.getKeyStroke("ctrl alt 3"));
                posTopLeft.addActionListener(new PopMenuListener());
                posTopLeft.add(posMenu);
            }
        }
    }

    private class PopMenuListener implements ActionListener {

        public PopMenuListener() {
            super();
        }


        public void posSet(PosSample pos) {
            posSample = pos;
            onSetPos();
        }

        public void posTopLeft()  { posSet(TOPLEFT); }
        public void posTopRight() { posSet(TOPRIGHT); }
        public void posBtmLeft()  { posSet(BTMLEFT); }
        public void posBtmRight() { posSet(BTMRIGHT); }

        public void actionPerformed(ActionEvent event) {
            {
                try {
                    Class[] paramTypes = new Class[]{};
                    Method m = this.getClass().getMethod(event.getActionCommand(), paramTypes);
                    if (m != null) {
                        Object[] argv = new Object[]{};
                        m.invoke(this, argv);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

}
