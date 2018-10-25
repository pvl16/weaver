package ru.weaver.loomGUI;

import ru.weaver.NotCreatePattern;
import ru.weaver.loom.Tabled;

import java.awt.*;

public class GUITabled extends GUIPattern {
    enum DirectShow{BTM_TOP, TOP_BTM}
    private Tabled tabled;
    private int tZoom;
    private DirectShow dshow;

    public GUITabled() throws NotCreatePattern {
        super();

        onSetZoom();
        dshow = DirectShow.BTM_TOP;

        tabled = new Tabled((short)10, (short)10, (short)4);
        setTitle("New Tabled");
        jPatPanel.setLayout(null);

    }

    protected void initParams() {
        parameters = new GUITabledParameters();
    }

    private void onSetZoom() { tZoom = 4 + 3 * zoom; }

}
