package com.example.mypc.demosqliterepository.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MyPC on 5/25/2017.
 */

public class Unit extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;

    public Unit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Unit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
