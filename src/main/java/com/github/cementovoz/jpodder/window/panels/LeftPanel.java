package com.github.cementovoz.jpodder.window.panels;


import com.github.cementovoz.jpodder.EventBus;
import com.github.cementovoz.jpodder.db.Connector;
import com.github.cementovoz.jpodder.db.models.Podcast;
import com.github.cementovoz.jpodder.events.ReloadPodcasts;
import com.github.cementovoz.jpodder.events.Start;
import com.google.inject.Inject;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

public class LeftPanel implements Panel {

    @Inject
    private EventBus eventBus;

    @Inject
    private Connector connector;

    private ListView<String> listView;

    /**
     * Create GUI for left panel
     *
     * @return
     */
    @Override
    public Node createGui() {
        listView = new ListView<>();
        eventBus.observable(Start.class)
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    List<Podcast> all = connector.podcastDao().getAll();
                    eventBus.post(new ReloadPodcasts(all
                            .stream()
                            .map(Podcast::getName)
                            .collect(Collectors.toList())
                    ));
                });
        eventBus.observable(ReloadPodcasts.class).subscribe(it -> {
            listView.getItems().clear();
            listView.getItems().addAll(it.getItems());
        });
        return listView;
    }
}
