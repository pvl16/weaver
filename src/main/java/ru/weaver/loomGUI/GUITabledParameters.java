package ru.weaver.loomGUI;

public class GUITabledParameters extends GUIPatternParameters {

    public boolean isGet() {
        super.isGet();
        res = true;
        return true;
    }

    public GUITabledParameters() {
        super();
        setTitle("New Tabled Parameters");
        this.setSize(500, 300);
    }

}
