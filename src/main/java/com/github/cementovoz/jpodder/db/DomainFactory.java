package com.github.cementovoz.jpodder.db;

import com.github.cementovoz.jpodder.db.dao.Podcasts;
import com.github.cementovoz.jpodder.db.models.Podcast;
import com.j256.ormlite.dao.DaoManager;

import javax.inject.Inject;

public class DomainFactory {

    private Connector connector;

    @Inject
    public DomainFactory(Connector connector) {
        this.connector = connector;
    }

    public Podcasts podcasts () {
        return DaoManager.lookupDao(connector.getSource(), Podcast.class);
    }

}
