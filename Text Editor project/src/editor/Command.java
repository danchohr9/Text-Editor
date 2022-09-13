package editor;

public interface Command {
    public static final String UNDO = "undo";
    public static final String REDO = "redo";
    public void execute(String command);
}