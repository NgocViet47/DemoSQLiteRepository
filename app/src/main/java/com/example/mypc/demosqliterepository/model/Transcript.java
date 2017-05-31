package com.example.mypc.demosqliterepository.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MyPC on 5/25/2017.
 */

public class Transcript  extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private int idUnit;
    private int idClass;
    private int idStudent;
    private int point15;
    private int point45;

    public Transcript(int id, String name, int idUnit, int idClass, int idStudent, int point15, int point45) {
        this.id = id;
        this.name = name;
        this.idUnit = idUnit;
        this.idClass = idClass;
        this.idStudent = idStudent;
        this.point15 = point15;
        this.point45 = point45;
    }

    public Transcript() {
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

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getPoint15() {
        return point15;
    }

    public void setPoint15(int point15) {
        this.point15 = point15;
    }

    public int getPoint45() {
        return point45;
    }

    public void setPoint45(int point45) {
        this.point45 = point45;
    }
}
