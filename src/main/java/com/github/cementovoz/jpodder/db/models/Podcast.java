package com.github.cementovoz.jpodder.db.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;


@DatabaseTable(tableName = "podcasts")
@Getter
@Setter
public class Podcast {
    @DatabaseField(id = true, allowGeneratedIdInsert = true)
    private Long id;
    @DatabaseField
    private String name;
    @DatabaseField(unique = true)
    private String url;
    @DatabaseField
    private String icon;
}
