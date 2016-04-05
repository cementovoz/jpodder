package com.github.cementovoz.jpodder;


import rx.Observable;
import rx.subjects.ReplaySubject;

public class EventBus {

    private final ReplaySubject events = ReplaySubject.create();

    public EventBus() {
        events.onErrorReturn(it -> null);
    }

    public void post(Object event) {
        events.onNext(event);
    }

    public Observable observable() {
        return events;
    }

    public <T> Observable<T> observable(Class<T> tClass) {
        return events.ofType(tClass);
    }

    public  Observable observable(Class aClass, Class bClass) {
        return events.ofType(aClass).mergeWith(events.ofType(bClass));
    }
}
