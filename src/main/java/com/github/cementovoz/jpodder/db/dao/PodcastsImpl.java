package com.github.cementovoz.jpodder.db.dao;

import com.github.cementovoz.jpodder.db.models.Podcast;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class PodcastsImpl extends BaseDaoImpl<Podcast, Long> implements Podcasts {

    public PodcastsImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Podcast.class);
    }

    @Override
    public List<Podcast> getAll() {
        try {
            return queryForAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
