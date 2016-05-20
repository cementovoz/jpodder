package com.github.cementovoz.jpodder.window.panels;


import com.github.cementovoz.jpodder.EventBus;
import com.github.cementovoz.jpodder.db.Connector;
import com.github.cementovoz.jpodder.db.DomainFactory;
import com.github.cementovoz.jpodder.db.models.Podcast;
import com.github.cementovoz.jpodder.events.ReloadPodcastsEvent;
import com.github.cementovoz.jpodder.events.StartEvent;
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
    private DomainFactory factory;

    private ListView<String> listView;

    /**
     * Create GUI for left panel
     *
     * @return
     */
    @Override
    public Node createGui() {
        listView = new ListView<>();
        eventBus.observable(StartEvent.class)
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    List<Podcast> all = factory.podcasts().getAll();
                    eventBus.post(new ReloadPodcastsEvent(all
                            .stream()
                            .map(Podcast::getName)
                            .collect(Collectors.toList())
                    ));
                });
        eventBus.observable(ReloadPodcastsEvent.class).subscribe(it -> {
            listView.getItems().clear();
            listView.getItems().addAll(it.getItems());
        });
        return listView;
    }
}
