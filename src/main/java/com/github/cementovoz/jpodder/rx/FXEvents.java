package com.github.cementovoz.jpodder.rx;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import rx.Observable;
import rx.Subscriber;

public class FXEvents {

    /**
     * Generate events from clicks for menu
     * @param node
     * @return
     */
    public static Observable<ActionEvent> events(MenuItem node) {
        return Observable.<ActionEvent>create(new Observable.OnSubscribe<ActionEvent>() {
            @Override
            public void call(Subscriber<? super ActionEvent> subscriber) {
                node.setOnAction((e) -> subscriber.onNext(e));
            }
        });
    }
}
