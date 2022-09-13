package editor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private TextArea textArea = new TextArea();

    @Override
    public void start(Stage stage) throws Exception{
//        create a menu
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu format = new Menu("Format");

//        create a menu item for File
        MenuItem newFile = new MenuItem("_New");
        MenuItem newWindow = new MenuItem("_New Window");
        MenuItem open = new MenuItem("_Open...");
        MenuItem save = new MenuItem("_Save");
        MenuItem saveAs = new MenuItem("_Save As...");
        MenuItem exit = new MenuItem("Exit");

        SeparatorMenuItem separator = new SeparatorMenuItem();

        file.getItems().addAll(newFile, newWindow, open, save, saveAs, exit);
        file.getItems().add(5, separator);


//        create a menu item for Edit
        MenuItem undo = new MenuItem("_Undo");
        MenuItem redo = new MenuItem("_Redo");
        MenuItem cut = new MenuItem("_Cut");
        MenuItem copy = new MenuItem("_Copy");
        MenuItem paste = new MenuItem("_Paste");
        MenuItem delete = new MenuItem("_Delete");
        MenuItem clear = new MenuItem("Clear");
        MenuItem find = new MenuItem("Find...");
        MenuItem findNext = new MenuItem("Find Next");
        MenuItem findPrevious = new MenuItem("Find Previous");
        MenuItem replace = new MenuItem("Replace...");
        MenuItem selectAll = new MenuItem("Select All");
        MenuItem time_date = new MenuItem("Time/Date");

        edit.getItems().addAll(undo,redo, cut, copy, paste, delete, clear, find, findNext, findPrevious,
                replace, selectAll, time_date);

        separator = new SeparatorMenuItem();
        edit.getItems().add(2, separator);
        separator = new SeparatorMenuItem();
        edit.getItems().add(8, separator);
        separator = new SeparatorMenuItem();
        edit.getItems().add(13, separator);

        MenuItem font = new MenuItem("Font...");
        format.getItems().addAll(font);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file, edit, format);


        Controller functions = new Controller();

        BorderPane borderPane = new BorderPane();
//        root.setMinSize(700, 500);
        borderPane.setTop(menuBar);
        borderPane.setCenter(textArea);
        Scene scene = new Scene(borderPane, 700, 500);

        newFile.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newWindow.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        exit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        redo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
        cut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        delete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        clear.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        find.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        findNext.setAccelerator(new KeyCodeCombination(KeyCode.F3));
        findPrevious.setAccelerator(new KeyCodeCombination(KeyCode.F3, KeyCombination.SHIFT_DOWN));
        replace.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        selectAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        time_date.setAccelerator(new KeyCodeCombination(KeyCode.F5));



        functions.onNewWindow(newWindow, textArea, menuBar, scene, stage);
        functions.onNew(newFile, saveAs, textArea, stage);
        functions.onOpen(open, textArea, stage);
        functions.onSaveAs(saveAs, textArea, stage);
        functions.onSave(save, textArea, stage);
        functions.onExit(exit, saveAs);
        functions.onCopy(copy,textArea);
        functions.onPaste(paste, textArea);
        functions.onClear(clear,textArea);
        functions.onDelete(delete,textArea);
        functions.onCut(cut,textArea);
        functions.onSelectAll(selectAll,textArea);
        functions.onTimeDate(time_date, textArea);
        functions.onFind(find, textArea, stage, findNext, findPrevious);
        functions.onReplace(replace, textArea, stage);
        functions.onFont(font, textArea, stage);
        functions.onUndo(undo);
        functions.onRedo(redo);
        functions.onEdit(edit, undo, cut, copy, delete, find, findNext, findPrevious, textArea);

        stage.setScene(scene);
        stage.setTitle("Text Editor");
        stage.show();


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (textArea.getLength() == 0) {
//                   do nothing
                } else {
                    exit.fire();
                    windowEvent.consume();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
