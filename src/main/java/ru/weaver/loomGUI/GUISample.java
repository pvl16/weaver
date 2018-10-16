package ru.weaver.loomGUI;

import ru.weaver.NotCreatePattern;
import ru.weaver.appGUI.pvlColorChooser;
import ru.weaver.loom.Sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Method;

import ru.weaver.appGUI.GUIUtils;

import static ru.weaver.loomGUI.GUISample.PosSample.*;

public class GUISample extends GUIPattern {
    enum PosSample{TOPLEFT, TOPRIGHT, BTMLEFT, BTMRIGHT}
    private Sample sample;
    private int tZoom;
    private PosSample posSample;
    private pvlColorChooser colorChooser;

    public GUISample() throws NotCreatePattern {
        super();

        onSetZoom();
        posSample = PosSample.BTMLEFT;

        sample = new Sample((short)4, (short)4, (short)50, (short)20, true, Color.GREEN, Color.RED);
        setTitle("New Sample");
        jPatPanel.setLayout(null);
        panView = new PanView();
        panWefts = new PanWefts();
        panWarps = new PanWarps();
        panSample = new PanSample();
        jPatPanel.add(panView);
        jPatPanel.add(panWefts);
        jPatPanel.add(panWarps);
        jPatPanel.add(panSample);
        makePop();
        onSetSize();
        this.pack();
        colorChooser = new pvlColorChooser();

    }

    protected void AfterChooseFileSave() {
        sample.saveToFile(filePath);
    }

    public boolean LoadFile(File filePath) {
        return false;
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
    }

    private void onSetPos() {
        panView.onSetPosSample();
        panWarps.onSetPosSample();
        panWefts.onSetPosSample();
        panSample.onSetPosSample();
        this.onSetSize();
    }

    private int getAXCount() { return sample.getCntWarps() + sample.getCntTreadles(); }
    private int getAYCount() { return sample.getCntWefts() + sample.getCntHeddles();  }

    private boolean isSampleTop()  { return ((posSample == TOPLEFT)||(posSample == TOPRIGHT)); }
    private boolean isSampleLeft() { return ((posSample == TOPLEFT)||(posSample == BTMLEFT));  }

    private class Pans extends JPanel {
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

        Pans() {
            this.setKXY();
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            this.onSetPosSample();
            this.onSetSize();
            this.addMouseListener(new pansMouseAdapter());
        }

        int getXCount() {
            return kXCnt * sample.getCntWarps() + (1 - kXCnt) * (sample.getCntTreadles() + 2);
        }

        int getYCount() {
            return kYCnt * sample.getCntWefts() + (1 - kYCnt) * (sample.getCntHeddles() + 2);
        }

        protected void setKXY() { kXCnt = 0; kYCnt = 0; }

        protected void onSetPosSample() {
            kXPos = 0;
            kYPos = 0;
        }

        void onSetSize() {
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

        protected void onClicked(int X, int Y, boolean isPop) {
        }

        private class pansMouseAdapter extends MouseAdapter {
            public void mouseClicked(MouseEvent e) {
                onClicked(e.getX(), e.getY(), e.isPopupTrigger());
            }
        }

    }

    private class PanView extends Pans {

        protected void setKXY() { kXCnt = 1; kYCnt = 1; }

        protected void onSetPosSample() {
            kXPos = (isSampleLeft()) ? 1 : 0;
            kYPos = (isSampleTop())  ? 1 : 0;
        }

        public void onAfterSetSize() {
            if ( isSampleLeft()) { bgX = 1;               dX =  tZoom; }
            else                 { bgX = szX - tZoom - 1; dX = -tZoom; }
            if ( isSampleTop())  { bgY = 1;               dY =  tZoom; }
            else                 { bgY = szY - tZoom - 1; dY = -tZoom; }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            short cntX = sample.getCntWarps();
            short cntY = sample.getCntWefts();
            int cX;
            int cY = bgY;

            for(short i = 0; i < cntY; i++) {
                cX = bgX;
                for (short j = 0; j < cntX ; j++) {
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
                short cntX = sample.getCntWarps();
                short cntY = sample.getCntHeddles();
                int cX = 0;
                int cY = bgY;

                g.setFont(new Font("Monospaced", Font.PLAIN, tZoom / 2));
                FontMetrics fm = g.getFontMetrics();
                Rectangle2D lm = fm.getStringBounds("50", g);
                int aX = (int) ((tZoom - lm.getWidth()) / 2);
                int aY = (int) ((tZoom - lm.getHeight()) / 2);

                for(short i = 0; i < cntY; i++) {
                    cX = bgX;
                    for (short j = 0; j < cntX ; j++) {
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
                for (short i = 0; i < cntX; i++) {
                    g.setColor(Color.WHITE);
                    g.fillRect(cX, cY, tZoom, tZoom);
                    g.setColor(sample.getColorWarp(i));
                    g.fillRect(cX, cY, tZoom, tZoom);
                    g.setColor(Color.BLACK);
                    g.drawRect(cX, cY, tZoom, tZoom);
                    g.drawRect(cX, cY + dY, tZoom, tZoom);
                    g.drawString(String.format("%2d", i + 1), cX + aX, cY + dY + tZoom - aY);
                    cX += dX;
                }
            } catch (Exception e) {
            }

        }

        protected void onClicked(int X, int Y, boolean isPop) {
            short nX = (short)(X / tZoom);
            short nY = (short)(Y / tZoom);

            if ((nX < 0) || (nX >= sample.getCntWarps())) return;
            if ((nY < 0) || (nY >= sample.getCntHeddles() + 2)) return;

            if (isPop) {
                popMenu.show(this, X, Y);
                return;
            }

            if (!isSampleLeft())
                nX = (short)(sample.getCntWarps() - nX - 1);
            if (isSampleTop())
                nY = (short)(sample.getCntHeddles() - nY + 1);

            if (nY == sample.getCntHeddles()) {
                Color c = colorChooser.getColor("choise Color", sample.getColorWarp(nX));
                if (c != null)
                    sample.setColorWarp(nX, c);
            } else if (nY < sample.getCntHeddles()) {
                sample.setHeddleWarp(nX, nY);
            }
            jPatPanel.repaint();
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
            try {
                short cntX = sample.getCntTreadles();
                short cntY = sample.getCntWefts();
                int cX = 0;
                int cY = bgY;

                g.setFont(new Font("Monospaced", Font.PLAIN, tZoom / 2));
                FontMetrics fm = g.getFontMetrics();
                Rectangle2D lm = fm.getStringBounds("50", g);
                int aX = (int) ((tZoom - lm.getWidth()) / 2);
                int aY = (int) ((tZoom - lm.getHeight()) / 2);

                for (short i = 0; i < cntY; i++) {
                    cX = bgX;
                    for (short j = 0; j < cntX; j++) {
                        g.setColor(Color.WHITE);
                        g.fillRect(cX, cY, tZoom, tZoom);
                        g.setColor(Color.BLACK);
                        g.drawRect(cX, cY, tZoom, tZoom);
                        if (sample.getTreadleWeft(i) == j)
                            g.fillRect(cX, cY, tZoom, tZoom);
                        cX += dX;
                    }

                    g.setColor(Color.WHITE);
                    g.fillRect(cX, cY, tZoom, tZoom);
                    g.fillRect(cX + dX, cY, tZoom, tZoom);
                    g.setColor(sample.getColorWeft(i));
                    g.fillRect(cX, cY, tZoom, tZoom);
                    g.setColor(Color.BLACK);
                    g.drawRect(cX, cY, tZoom, tZoom);
                    g.drawRect(cX + dX, cY, tZoom, tZoom);
                    g.drawString(String.format("%2d", i + 1), cX + dX + aX, cY + tZoom - aY);

                    cY += dY;
                }
            } catch (Exception e) {
            }
        }

        protected void onClicked(int X, int Y, boolean isPop) {
            short nX = (short)(X / tZoom);
            short nY = (short)(Y / tZoom);

            if ((nX < 0) || (nX >= sample.getCntTreadles() + 2)) return;
            if ((nY < 0) || (nY >= sample.getCntWefts())) return;

            if (isPop) {
                popMenu.show(this, X, Y);
                return;
            }

            if (isSampleLeft())
                nX = (short)(sample.getCntTreadles() - nX + 1);
            if (!isSampleTop())
                nY = (short)(sample.getCntWefts() - nY - 1);

            if (nX == sample.getCntTreadles()) {
                Color c = colorChooser.getColor("choise Color", sample.getColorWeft(nY));
                if (c != null)
                    sample.setColorWeft(nY, c);
            } else if (nX < sample.getCntTreadles()){
                sample.setTreadleWeft(nY, nX);
            }
            jPatPanel.repaint();
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
            short cntX = sample.getCntTreadles();
            short cntY = sample.getCntHeddles();
            int cX = 0;
            int cY = bgY;

            for(short i = 0; i < cntY; i++) {
                cX = bgX;
                for (short j = 0; j < cntX ; j++) {
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

        protected void onClicked(int X, int Y, boolean isPop) {
            short nX = (short)(X / tZoom);
            short nY = (short)(Y / tZoom);

            if ((nX < 0) || (nX >= sample.getCntTreadles() + 2)) return;
            if ((nY < 0) || (nY >= sample.getCntHeddles() + 2)) return;

            if (isPop) {
                popMenu.show(this, X, Y);
                return;
            }

            if (isSampleLeft())
                nX = (short)(sample.getCntTreadles() - nX + 1);
            if (isSampleTop())
                nY = (short)(sample.getCntHeddles() - nY + 1);

            try {
                if ((nX < sample.getCntTreadles()) && (nY < sample.getCntHeddles())) {
                    boolean isUp = sample.getIsSampleUp(nY, nX);
                    sample.setIsSampleUp(nY, nX, !isUp);
                }
            } catch (Exception e) {
            }

            jPatPanel.repaint();

        }

    }

    private PanView panView;
    private PanWarps panWarps;
    private PanWefts panWefts;
    private PanSample panSample;

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

    public void posSet(PosSample pos) {
        posSample = pos;
        onSetPos();
    }

    public void posTopLeft()  { posSet(TOPLEFT); }
    public void posTopRight() { posSet(TOPRIGHT); }
    public void posBtmLeft()  { posSet(BTMLEFT); }
    public void posBtmRight() { posSet(BTMRIGHT); }

    private class PopMenuListener implements ActionListener {

        public PopMenuListener() {
            super();
        }


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
