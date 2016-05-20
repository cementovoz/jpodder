package com.github.cementovoz.jpodder.window;

import com.github.cementovoz.jpodder.window.panels.ContentPanel;
import com.github.cementovoz.jpodder.window.panels.LeftPanel;
import com.github.cementovoz.jpodder.window.panels.MenuPanel;
import com.github.cementovoz.jpodder.window.panels.StatusPanel;
import com.google.inject.Inject;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
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
        pane.setTop(menuPanel.createGui());
        SplitPane splitPane = new SplitPane();
        Node left = leftPanel.createGui();
        splitPane.getItems().addAll(left, contentPanel.createGui());
        splitPane.setDividerPositions(0.25);
        pane.setCenter(splitPane);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("JPodder - Listen");
        stage.show();
    }
}
