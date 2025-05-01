package model;

import java.io.Serializable;

public class Buffer implements Serializable {

  private static final long serialVersionUID = 1L;
  public static final int IN = 0;
  public static final int OUT = 1;

  String content;
  boolean active;

  public Buffer() {
    active = false;
    content = "";
  }

  public Buffer(String input) {
    content = input;
    active = false;
  }

  public void setActive(boolean act) {
    active = act;
  }

  public boolean getActive() {
    return active;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String text) {
    content = text;
  }
}
