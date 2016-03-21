package com.github.cementovoz.jpodder.window;

import com.github.cementovoz.jpodder.window.panels.ContentPanel;
import com.github.cementovoz.jpodder.window.panels.LeftPanel;
import com.github.cementovoz.jpodder.window.panels.MenuPanel;
import com.github.cementovoz.jpodder.window.panels.StatusPanel;
import com.google.inject.Inject;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {

    @Inject
    private LeftPanel leftPanel;
    @Inject
    private ContentPanel contentPanel;
    @Inject
    private MenuPanel menuPanel;
    @Inject
    private StatusPanel statusPanel;

    public void show (Stage stage) {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane);
        pane.setCenter(contentPanel.createGui());
        pane.setLeft(leftPanel.createGui());
        stage.setScene(scene);
        stage.show();
    }
}
