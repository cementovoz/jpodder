package com.github.cementovoz.jpodder.events;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReloadPodcasts {
    private List<String> items;

    public ReloadPodcasts(List<String> items) {
        this.items = items;
    }
}
