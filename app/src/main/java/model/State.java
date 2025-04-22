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

  public Buffer createBuffer(int index) {
    Buffer buf = new Buffer();
    bufferList.add(index, buf);
    return buf;
  }

  public void deactivateBuffer(int index) {
    bufferList.get(index).setActive(false);
  }

  public void activateBuffer(int index) {
    bufferList.get(index).setActive(true);
  }

  public int getActiveBufIndex() {
    for (int i = 0; i < bufferList.size(); ++i) {
      if (bufferList.get(i).getActive() == true) return i;
    }
    return -1;
  }

  public ArrayList<Buffer> getBuffers() {
    return bufferList;
  }
}
