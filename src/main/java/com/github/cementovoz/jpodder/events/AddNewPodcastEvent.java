package com.github.cementovoz.jpodder.events;

import lombok.Getter;

public class AddNewPodcastEvent {
    @Getter
    private String url;

    public AddNewPodcastEvent(String url) {
        this.url = url;
    }

    public static class ShowDialod {}
}
