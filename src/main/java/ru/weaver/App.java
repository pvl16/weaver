package ru.weaver;

import ru.weaver.appGUI.GUI;

public class App {

  public static void main(String[] args) {
    try {
      GUI gui = new GUI();
      gui.init();
//            runUI();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
