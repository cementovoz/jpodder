package com.github.cementovoz.jpodder.db.dao;

import com.github.cementovoz.jpodder.db.models.Podcast;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public interface PodcastDao extends Dao<Podcast, Long> {
    List<Podcast> getAll();
}
