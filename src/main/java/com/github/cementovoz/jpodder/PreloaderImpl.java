package com.github.cementovoz.jpodder;

import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreloaderImpl extends Preloader {

    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        AnchorPane root = new AnchorPane();
        root.setBackground(new Background(
                new BackgroundImage(
                        new Image(getClass().getResourceAsStream("/images/logo-256.png")),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT
                )));
        Label label = new Label("JPODDER");
        label.setTextFill(Paint.valueOf("white"));
        VBox grid = new VBox(label);
        HBox panel = new HBox(grid);
        root.getChildren().add(panel);
        panel.setAlignment(Pos.CENTER);
        panel.setPrefWidth(256);
        AnchorPane.setBottomAnchor(panel, 30d);
        Scene scene = new Scene(root, 256, 256);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}
