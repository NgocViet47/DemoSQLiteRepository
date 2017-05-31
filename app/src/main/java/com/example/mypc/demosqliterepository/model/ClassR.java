package com.example.mypc.demosqliterepository.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MyPC on 5/25/2017.
 */

public class ClassR extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private int idUnit;

    public ClassR() {
    }

    public ClassR(int id, String name, int idUnit) {
        this.id = id;
        this.name = name;
        this.idUnit = idUnit;
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

    public int getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }
}
