package model;

import java.util.ArrayList;

public class State {
  ArrayList<Buffer> bufferList;
  boolean degreeMode;
  boolean largeFont;

  public State() {
    bufferList = new ArrayList<Buffer>();
    degreeMode = true;
    largeFont = false;
  }
}
