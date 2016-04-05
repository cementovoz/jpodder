package com.github.cementovoz.jpodder.window;

import com.github.cementovoz.jpodder.EventBus;
import com.github.cementovoz.jpodder.events.AddNewPodcast;
import com.google.inject.Inject;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import org.apache.commons.validator.routines.UrlValidator;

public class AddDialog {

    @Inject
    public AddDialog(EventBus eventBus) {
        eventBus.observable(AddNewPodcast.ShowDialod.class).subscribe(it -> {
            TextInputDialog alert = new TextInputDialog();
            alert.setTitle("Add podcast url");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.getEditor().textProperty().addListener((v, o, n) -> {
                if (!new UrlValidator(new String[]{"http", "https"}).isValid(n)) {
                    alert.getEditor().setStyle("-fx-border-color: #ff3a38");
                    alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
                } else {
                    alert.getEditor().setStyle("-fx-border-color: null");
                    alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                }
            });
            alert.showAndWait();
            String url = alert.getResult();
            if (url != null && !url.isEmpty()) {
                eventBus.post(new AddNewPodcast(url));
            }
        });
    }
}
