package com.github.cementovoz.jpodder.db;

import com.github.cementovoz.jpodder.EventBus;
import com.github.cementovoz.jpodder.db.dao.PodcastsImpl;
import com.github.cementovoz.jpodder.db.models.Podcast;
import com.github.cementovoz.jpodder.db.models.Series;
import com.google.inject.Inject;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connector {

    private static final Logger LOGGER = LoggerFactory.getLogger(Connector.class);
    @Getter
    private ConnectionSource source;
    private EventBus eventBus;

    @Inject
    public Connector(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    public void initialize() {
        try {
            connect();
            createTablesIfNotExists();
            registerDao();
        } catch (Exception e) {
            LOGGER.error("Failed create tables", e);
            eventBus.post(e);
        }
    }

    private void registerDao() throws Exception {
        DaoManager.registerDao(source, new PodcastsImpl(source));
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
