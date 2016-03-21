package com.github.cementovoz.jpodder;


import rx.Observable;
import rx.subjects.PublishSubject;

public class EventBus {

    private final PublishSubject events = PublishSubject.create();

    public void post(Object event) {
        events.onNext(event);
    }

    public Observable observable() {
        return events;
    }

    public Observable observable(Class tClass) {
        return events.ofType(tClass);
    }
}
