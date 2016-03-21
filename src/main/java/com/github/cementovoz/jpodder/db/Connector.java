package com.github.cementovoz.jpodder.db;

import com.github.cementovoz.jpodder.EventBus;
import com.github.cementovoz.jpodder.db.models.Podcast;
import com.github.cementovoz.jpodder.db.models.Series;
import com.github.cementovoz.jpodder.events.Initialize;
import com.google.inject.Inject;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.apache.log4j.Logger;
import rx.schedulers.Schedulers;

public class Connector {

    private static final Logger LOGGER = Logger.getLogger(Connector.class);

    private ConnectionSource source;
    private EventBus eventBus;

    @Inject
    public Connector(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.observable(Initialize.class)
                .observeOn(Schedulers.io())
                .subscribe(it -> {
                    try {
                        connect();
                        createTablesIfNotExists();
                    } catch (Exception e) {
                        LOGGER.error(e);
                        eventBus.post(e);
                    }
                });
    }

    private void connect() throws Exception {
        String databaseUrl = "jdbc:h2:mem:jpodder";
        source = new JdbcConnectionSource(databaseUrl);
    }

    private void createTablesIfNotExists() throws Exception {
        TableUtils.createTableIfNotExists(source, Podcast.class);
        TableUtils.createTableIfNotExists(source, Series.class);
    }
}
