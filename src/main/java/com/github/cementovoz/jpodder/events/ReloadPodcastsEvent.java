package com.github.cementovoz.jpodder.events;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReloadPodcastsEvent implements Event {
    private List<String> items;

    public ReloadPodcastsEvent(List<String> items) {
        this.items = items;
    }
}
