package model;

/**
 * A data buffer that stores content and maintains an active state flag.
 * Used to manage input/output operations with status tracking.
 * Contains constants for buffer types (IN/OUT) and provides basic content management.
 */
public class Buffer {

    /** Constant representing an input buffer type */
    public static final int IN = 0;
    
    /** Constant representing an output buffer type */
    public static final int OUT = 1;

    /** The content stored in this buffer */
    String content;
    
    /** Flag indicating whether this buffer is currently active */
    boolean active;

    /**
     * Constructs an empty inactive buffer.
     * Initializes with no content and inactive state.
     */
    public Buffer() {
        active = false;
    }

    /**
     * Constructs a buffer with specified content.
     * Initializes as inactive with the provided content.
     *
     * @param input the initial content to store in the buffer
     */
    public Buffer(String input) {
        content = input;
        active = false;
    }

    /**
     * Sets the active state of this buffer.
     *
     * @param act the active state to set (true for active, false for inactive)
     */
    public void setActive(boolean act) {
        active = act;
    }

    /**
     * Gets the current active state of this buffer.
     *
     * @return true if the buffer is active, false otherwise
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Gets the current content of this buffer.
     *
     * @return the content stored in the buffer
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of this buffer.
     *
     * @param text the content to store in the buffer
     */
    public void setContent(String text) {
        content = text;
    }
}