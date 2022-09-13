package editor;

import javafx.scene.input.Clipboard;

public class Paste {

    public Paste(){}

    public String pasteFromClipboard(){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        String info = clipboard.getString();
        return info;
    }
}
