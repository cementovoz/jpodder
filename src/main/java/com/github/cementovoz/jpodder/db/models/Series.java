package com.github.cementovoz.jpodder.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@DatabaseTable(tableName = "series")
@Getter
@Setter
public class Series {
    @DatabaseField(id = true, allowGeneratedIdInsert = true)
    private Long id;
    @DatabaseField(foreign = true)
    private Podcast podcast;
    @DatabaseField
    private String url;
    @DatabaseField
    private String icon;
    @DatabaseField
    private String description;
    @DatabaseField
    private boolean downloaded = false;
    @DatabaseField
    private boolean listened = false;
    @DatabaseField
    private int currentTime = 0;
}
