package com.github.cementovoz.jpodder.events;

import lombok.Getter;

public class AddNewPodcast {
    @Getter
    private String url;

    public AddNewPodcast(String url) {
        this.url = url;
    }

    public static class ShowDialod {}
}
