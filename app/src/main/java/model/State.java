package model;

import java.util.ArrayList;

/**
 * Manages the application state including buffers and display settings.
 * Maintains a list of buffers and tracks their active states.
 * Controls global settings like angle mode (degrees/radians) and font size.
 */
public class State {
    /** List of buffers managed by this state */
    private static ArrayList<Buffer> bufferList;
    
    /** Flag indicating whether angle calculations should use degrees (true) or radians (false) */
    private boolean degreeMode;
    
    /** Flag indicating whether large font should be used for display */
    private boolean largeFont;

    /**
     * Constructs a new State with default settings.
     * Initializes:
     * - Empty buffer list
     * - Degree mode enabled (true)
     * - Normal font size (largeFont false)
     */
    public State() {
        bufferList = new ArrayList<Buffer>();
        degreeMode = true;
        largeFont = false;
    }

    /**
     * Creates and adds a new buffer at the specified index.
     * 
     * @param index the position in the buffer list to add the new buffer
     * @return the newly created Buffer instance
     */
    public Buffer createBuffer(int index) {
        Buffer buf = new Buffer();
        bufferList.add(index, buf);
        return buf;
    }

    /**
     * Deactivates the buffer at the specified index.
     * 
     * @param index the position of the buffer to deactivate
     */
    public void deactivateBuffer(int index) {
        bufferList.get(index).setActive(false);
    }

    /**
     * Activates the buffer at the specified index.
     * 
     * @param index the position of the buffer to activate
     */
    public void activateBuffer(int index) {
        bufferList.get(index).setActive(true);
    }

    /**
     * Gets the index of the currently active buffer.
     * 
     * @return the index of the active buffer, or -1 if no buffer is active
     */
    public int getActiveBufIndex() {
        for (int i = 0; i < bufferList.size(); ++i) {
            if (bufferList.get(i).getActive() == true) return i;
        }
        return -1;
    }

  public ArrayList<Buffer> getBuffers() {
    return bufferList;
  }
  //private boolean degreeMode = true; // default to DEG

public void toggleMode() {
    degreeMode = !degreeMode;
}

public boolean isDegreeMode() {
    return degreeMode;
}

}