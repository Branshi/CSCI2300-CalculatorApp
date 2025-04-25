package model;

import java.util.ArrayList;

public class State {
  private ArrayList<Buffer> bufferList;

  private boolean degreeMode;

  private boolean largeFont;

  public State() {
    bufferList = new ArrayList<Buffer>();
    degreeMode = true;
    largeFont = false;
  }

  public void setDegreeMode(boolean b) {
    degreeMode = b;
  }

  public boolean getDegreeMode() {
    return degreeMode;
  }

  public void setLargeFont(boolean b) {
    largeFont = b;
  }

  public boolean getLargeFont() {
    return largeFont;
  }

  public Buffer createBuffer(int index) {
    Buffer buf = new Buffer();
    bufferList.add(index, buf);
    return buf;
  }

  public Buffer removeBuffer(int index) {
    Buffer b = bufferList.remove(index);
    return b;
  }

  public void deactivateBuffers() {
    for (Buffer buf : bufferList) {
      buf.setActive(false);
    }
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
