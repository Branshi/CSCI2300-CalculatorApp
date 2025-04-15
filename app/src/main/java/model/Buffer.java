package model;

public class Buffer {

  public static final int IN = 0;
  public static final int OUT = 1;

  String[] content;
  boolean active;

  public Buffer() {
    content = new String[2];
    active = false;
  }

  public Buffer(String input) {
    content[IN] = input;
    active = false;
  }

  public Buffer(String input, String output) {
    content[IN] = input;
    content[OUT] = output;
    active = false;
  }

  public void toggleActive() {
    active = !active;
  }

  public boolean getActive() {
    return active;
  }

  public String[] getContent() {
    return content;
  }
}
