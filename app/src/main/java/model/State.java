package model;

import java.io.*;
import java.util.ArrayList;

public class State implements Serializable {
  private static final long serialVersionUID = 1L;
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

  public void clearBuffers() {
    bufferList.subList(0, bufferList.size() - 1).clear();
    bufferList.get(0).setContent("");
    bufferList.get(0).setActive(true);
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

  public void saveTo(String filePath) throws IOException {
    try (FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(this);
    }
  }

  public static State loadFrom(String filePath) throws IOException, ClassNotFoundException {
    try (FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
      return (State) ois.readObject();
    }
  }
}
