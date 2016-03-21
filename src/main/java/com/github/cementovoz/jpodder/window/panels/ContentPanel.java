package com.github.cementovoz.jpodder.window.panels;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ContentPanel implements Panel{

    @Override
    public Node createGui() {
        return new AnchorPane(new Label("TEST"));
    }
}
