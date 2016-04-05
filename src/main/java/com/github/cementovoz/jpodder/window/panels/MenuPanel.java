package com.github.cementovoz.jpodder.window.panels;

import com.github.cementovoz.jpodder.EventBus;
import com.github.cementovoz.jpodder.events.AddNewPodcast;
import com.github.cementovoz.jpodder.rx.FXEvents;
import com.google.inject.Inject;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuPanel implements Panel {

    @Inject
    private EventBus eventBus;

    @Override
    public Node createGui() {
        MenuBar menuBar = new MenuBar();
        Menu podcasts = new Menu("Podcasts");
        MenuItem add = new MenuItem("Add");
        FXEvents.events(add).subscribe(it -> eventBus.post(new AddNewPodcast.ShowDialod()));
        podcasts.getItems().add(add);
        menuBar.getMenus().add(podcasts);
        return menuBar;
    }
}
