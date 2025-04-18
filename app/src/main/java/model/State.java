package model;

import java.util.ArrayList;

public class State {
  private static ArrayList<Buffer> bufferList;
  private boolean degreeMode;
  private boolean largeFont;

  public State() {
    bufferList = new ArrayList<Buffer>();
    degreeMode = true;
    largeFont = false;
  }

  public Buffer createBuffer() {
    Buffer buf = new Buffer();
    bufferList.add(buf);
    return buf;
  }

  public ArrayList<Buffer> getBuffers() {
    return bufferList;
  }
}
