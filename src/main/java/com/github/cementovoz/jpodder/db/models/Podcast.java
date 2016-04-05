package com.github.cementovoz.jpodder.db.models;

import com.github.cementovoz.jpodder.db.dao.PodcastDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;


@DatabaseTable(tableName = "podcasts", daoClass = PodcastDaoImpl.class)
@Getter
@Setter
public class Podcast {
    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String name;
    @DatabaseField(unique = true)
    private String url;
    @DatabaseField
    private String icon;
}
