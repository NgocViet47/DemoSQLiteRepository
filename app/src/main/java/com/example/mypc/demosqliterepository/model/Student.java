package com.example.mypc.demosqliterepository.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MyPC on 5/25/2017.
 */

public class Student extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private int idUnit;
    private int idClass;
    private String birthDate;
    private String address;

    public Student() {
    }

    public Student(int id, String name, int idUnit, int idClass, String birthDate,String address) {
        this.id = id;
        this.name = name;
        this.idUnit = idUnit;
        this.idClass = idClass;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
