package com.github.cementovoz.jpodder.service;

import com.github.cementovoz.jpodder.EventBus;
import com.github.cementovoz.jpodder.events.AddNewPodcastEvent;
import com.google.inject.Inject;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.net.URL;

public class PodcastService {

    private EventBus eventBus;

    @Inject
    public PodcastService(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.observable(AddNewPodcastEvent.class)
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    loadRss(it.getUrl());
        });
    }

    private void loadRss(String url) {
        SyndFeedInput input = new SyndFeedInput();
        try {
            SyndFeed feed = input.build(new XmlReader(new URL(url)));

            for (SyndEntry syndEntry : feed.getEntries()) {
                String author = syndEntry.getAuthor();
                String link = syndEntry.getLink();
                String title = syndEntry.getTitle();
                System.out.println(author +" => "+ link + " => " + title);
            }
        } catch (FeedException | IOException e) {
            eventBus.post(e);
        }
    }
}
