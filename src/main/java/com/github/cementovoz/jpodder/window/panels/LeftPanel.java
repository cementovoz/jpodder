package com.github.cementovoz.jpodder.window.panels;


import com.github.cementovoz.jpodder.EventBus;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class LeftPanel implements Panel {

    @Inject
    private EventBus eventBus;

    @Override
    public Node createGui() {

        return new ListView<>(FXCollections.observableArrayList("Test 1", "Test 2"));
    }
}
