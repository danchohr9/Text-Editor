package editor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Font {
    private Stage fontStage;
    private GridPane gridPane = new GridPane();
    private Scene fontScene;
    private TextArea textArea;

    public Font(TextArea textArea){
        fontScene = new Scene(gridPane, 400, 200);
        this.textArea = textArea;
    }

    public void createNonModalDialog(Stage primaryStage){
        try {
            fontStage = new Stage();
            fontStage.setScene(fontScene);
            fontStage.initOwner(primaryStage);
            fontStage.setAlwaysOnTop(false);
            fontStage.setTitle("Font");
            fontStage.setResizable(false);
            createDialogElements();
            fontStage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createDialogElements(){
        Label fontLbl = new Label("Choose font:");
        Label fontStyleLbl = new Label("Choose font style:");
        Label sizeLbl = new Label("Choose size:");
        ComboBox<String> fonts = new ComboBox<>();
        ComboBox<String> fontStyles = new ComboBox<>();
        ComboBox<Integer> sizes = new ComboBox<>();
        fonts.getItems().addAll("Complex", "Calibri", "Txt", "Times New Roman", "Arial", "Verdana");
        fontStyles.getItems().addAll("NORMAL", "BOLD", "THIN", "BLACK");
        fontStyles.getSelectionModel().selectFirst();
        fonts.getSelectionModel().selectFirst();
        sizes.getItems().addAll(12, 24, 36, 48, 60, 72);
        sizes.getSelectionModel().selectFirst();

        Button okButton = new Button("Ok");
        Button cancelButton = new Button("Cancel");
        okButton.defaultButtonProperty();
        cancelButton.cancelButtonProperty();

//        gridPane.addRow(0, fontLbl, fontStyleLbl, sizeLbl);
//        gridPane.addRow(1, fonts, fontStyles, sizes, okButton, cancelButton);

        gridPane.add(fontLbl, 0,0);
        gridPane.add(fontStyleLbl, 1,0);
        gridPane.add(sizeLbl, 2,0);
        gridPane.add(fonts, 0,1);
        gridPane.add(fontStyles, 1,1);
        gridPane.add(sizes, 2,1);
        gridPane.add(okButton, 0,2);
        gridPane.add(cancelButton, 1,2);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10.0);
        gridPane.setHgap(10.0);

        okButton.setPrefSize(70,20);
        cancelButton.setPrefSize(70,20);
        fonts.setPrefSize(140,20);
        fontStyles.setPrefSize(100,20);
        sizes.setPrefSize(70,20);

        okButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        cancelButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        fontLbl.setStyle("-fx-font: normal bold 15px 'serif' ");
        fontStyleLbl.setStyle("-fx-font: normal bold 15px 'serif' ");
        sizeLbl.setStyle("-fx-font: normal bold 15px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        onButtonOk(okButton, fonts, fontStyles, sizes);
        onButtonCancel(cancelButton);
    }

    public void onButtonCancel(Button cancelButton){
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fontStage.close();
            }
        });
    }

    public void onButtonOk(Button okButton, ComboBox<String> fonts, ComboBox<String> fontStyles, ComboBox<Integer> sizes){
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String f = fonts.getValue();
                String fS = fontStyles.getValue();
                Integer s = sizes.getValue();

                textArea.setFont(javafx.scene.text.Font.font(f, FontWeight.valueOf(fS), s));

                fontStage.close();
            }
        });
    }


    public boolean checkIfInitialized(){
        if(fontStage == null) return true;
        return false;
    }

    public void showDialog(){
        fontStage.show();
    }

}