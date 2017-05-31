package com.example.mypc.demosqliterepository.service;

import android.content.Context;

import com.example.mypc.demosqliterepository.model.ClassR;
import com.example.mypc.demosqliterepository.model.Student;
import com.example.mypc.demosqliterepository.model.Transcript;
import com.example.mypc.demosqliterepository.model.Unit;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static com.example.mypc.demosqliterepository.service.BaseRealmUtils.getRealmConfig;

/**
 * Created by MyPC on 5/29/2017.
 */

public class RealmService {
    private Realm realm;
    private static final int REALM_VERSION = 1;
    private static final String NAME_REALM = "default1.realm";
    private static final String ID = "id";
    private static final String ID_UNIT = "idUnit";
    private static final String ID_CLASS = "idClass";
    private static final String ID_STUDENT = "idStudent";
    public RealmService(Context context){
        realm = Realm.getInstance(getRealmConfig(context));
    }
    public void addUnit(final Unit mUnit) {
        if(mUnit!=null) {
            realm.beginTransaction();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    int nextID = 1;
                    if(realm.where(Unit.class)!=null){
                        nextID = realm.where(Unit.class).max(ID).intValue() + 1;
                    }
                    Unit unit = realm.createObject(Unit.class,nextID);
                    unit.setName(mUnit.getName());
                }
            });
            realm.commitTransaction();
        }
    }
    public void addClass(final ClassR mClassR) {
        if(mClassR!=null) {
            realm.beginTransaction();
            int nextID = 1;
            if(realm.where(ClassR.class).findFirst()!=null){
                nextID = realm.where(ClassR.class).max(ID).intValue() + 1;
            }
            ClassR classR = realm.createObject(ClassR.class,nextID);
            classR.setIdUnit(mClassR.getIdUnit());
            classR.setName(mClassR.getName());
            realm.commitTransaction();
        }
    }
    public void addStudent(final Student mStudent) {
        if(mStudent!=null) {
            realm.beginTransaction();
            int nextID = 1;
            if(realm.where(Student.class).findFirst()!=null){
                nextID = realm.where(Student.class).max(ID).intValue() + 1;
            }
            Student student = realm.createObject(Student.class,nextID);
            student.setIdUnit(mStudent.getIdUnit());
            student.setIdClass(mStudent.getIdClass());
            student.setName(mStudent.getName());
            student.setBirthDate(mStudent.getBirthDate());
            realm.commitTransaction();
        }
    }
    public void addTranscript(final Transcript mTranscript) {
        if(mTranscript!=null) {
            realm.beginTransaction();
            int nextID = 1;
            if(realm.where(Transcript.class).findFirst()!=null){
                nextID = realm.where(Transcript.class).max(ID).intValue() + 1;
            }
            Transcript transcript = realm.createObject(Transcript.class,nextID);
            transcript.setIdUnit(mTranscript.getIdUnit());
            transcript.setIdClass(mTranscript.getIdClass());
            transcript.setIdStudent(mTranscript.getIdStudent());
            transcript.setPoint15(mTranscript.getPoint15());
            transcript.setPoint45(mTranscript.getPoint45());
            transcript.setName(mTranscript.getName());
            realm.commitTransaction();
        }
    }

    public List<Unit> getAllUnit() {
        RealmQuery<Unit> query = realm.where(Unit.class);
        RealmResults<Unit> result = query.findAll();
        if (result != null) {
            return realm.copyFromRealm(result);
        }
        return new ArrayList<>();
    }

    public List<ClassR> getAllClass() {
        RealmQuery<ClassR> query = realm.where(ClassR.class);
        RealmResults<ClassR> result = query.findAll();
        if (result != null) {
            return realm.copyFromRealm(result);
        }
        return new ArrayList<>();
    }
    public List<ClassR> getListClassByIdUnit(int id) {
        RealmQuery<ClassR> query = realm.where(ClassR.class).equalTo(ID_UNIT, id);
        RealmResults<ClassR> result = query.findAll();
        if (result != null) {
            return realm.copyFromRealm(result);
        }
        return new ArrayList<>();
    }

    public List<Student> getAllStudent() {
        RealmQuery<Student> query = realm.where(Student.class);
        RealmResults<Student> result = query.findAll();
        if (result != null) {
            return realm.copyFromRealm(result);
        }
        return new ArrayList<>();
    }
    public List<Student> getListStudentByIdClass(int id) {
        RealmQuery<Student> query = realm.where(Student.class).equalTo(ID_CLASS, id);
        RealmResults<Student> result = query.findAll();
        if (result != null) {
            return realm.copyFromRealm(result);
        }
        return new ArrayList<>();
    }

    public List<Transcript> getAllTranscript() {
        RealmQuery<Transcript> query = realm.where(Transcript.class);
        RealmResults<Transcript> result = query.findAll();
        if (result != null) {
            return realm.copyFromRealm(result);
        }
        return new ArrayList<>();
    }
    public List<Transcript> getListTranscriptByIdStudent(int id) {
        RealmQuery<Transcript> query = realm.where(Transcript.class).equalTo(ID_STUDENT, id);
        RealmResults<Transcript> result = query.findAll();
        if (result != null) {
            return realm.copyFromRealm(result);
        }
        return new ArrayList<>();
    }
    public Unit getUnit(int id) {
        Unit object = realm.where(Unit.class).equalTo(ID, id).findFirst();
        return realm.copyFromRealm(object);
    }

    public ClassR getClass(int id) {
        ClassR object = realm.where(ClassR.class).equalTo(ID, id).findFirst();
        return realm.copyFromRealm(object);
    }

    public Student getStudent(int id) {
        RealmResults<Student> object = realm.where(Student.class).equalTo(ID, id).findAll();
        return realm.copyFromRealm(object.first());
    }

    public Transcript getTranscript(int id) {
        Transcript object = realm.where(Transcript.class).equalTo(ID, id).findFirst();
        return realm.copyFromRealm(object);
    }


    /*private Realm realm = Realm.getDefaultInstance();

    public void addUnit(final Unit mUnit) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Unit unit = realm.createObject(Unit.class);
                unit.setId(mUnit.getId());
                unit.setName(mUnit.getName());
            }
        });

        realm.commitTransaction();
    }

    public void addClass(ClassR mClassR) {
        realm.beginTransaction();
        ClassR classR = realm.createObject(ClassR.class);
        classR.setId(mClassR.getId());
        classR.setIdUnit(mClassR.getIdUnit());
        classR.setName(mClassR.getName());
        realm.commitTransaction();
    }

    public void addStudent(Student mStudent) {
        realm.beginTransaction();
        Student student = realm.createObject(Student.class);
        student.setId(mStudent.getId());
        student.setIdUnit(mStudent.getIdUnit());
        student.setIdClass(mStudent.getIdClass());
        student.setName(mStudent.getName());
        student.setBirthDate(mStudent.getBirthDate());
        realm.commitTransaction();
    }

    public void addTranscript(Transcript mTranscript) {
        realm.beginTransaction();
        Transcript transcript = realm.createObject(Transcript.class);
        transcript.setId(mTranscript.getId());
        transcript.setIdUnit(mTranscript.getIdUnit());
        transcript.setIdClass(mTranscript.getIdClass());
        transcript.setIdStudent(mTranscript.getIdStudent());
        transcript.setPoint15(mTranscript.getPoint15());
        transcript.setPoint45(mTranscript.getPoint45());
        transcript.setName(mTranscript.getName());
        realm.commitTransaction();
    }

    public List<Unit> getAllUnit() {
        realm = Realm.getDefaultInstance();
        List<Unit> listUnit = new ArrayList<>();
        for (Unit unit : realm.where(Unit.class).findAll()) {
            listUnit.add(unit);
        }
        return listUnit;
    }

    public List<ClassR> getAllClass() {
        List<ClassR> listClass = new ArrayList<>();
        for (ClassR classR : realm.where(ClassR.class).findAll()) {
            listClass.add(classR);
        }
        return listClass;
    }

    public List<ClassR> getListClassByIdUnit(int id) {
        List<ClassR> listClass = new ArrayList<>();
        for (ClassR classR : realm.where(ClassR.class).equalTo("idUnit", id).findAll()) {
            listClass.add(classR);
        }
        return listClass;
    }

    public List<Student> getAllStudent() {
        List<Student> listStudent = new ArrayList<>();
        for (Student student : realm.where(Student.class).findAll()) {
            listStudent.add(student);
        }
        return listStudent;
    }

    public List<Student> getListStudentByIdClass(int id) {
        List<Student> listStudent = new ArrayList<>();
        for (Student student : realm.where(Student.class).equalTo("idClass", id).findAll()) {
            listStudent.add(student);
        }
        return listStudent;
    }

    public List<Transcript> getAllTranscript() {
        List<Transcript> listTranscript = new ArrayList<>();
        for (Transcript transcript : realm.where(Transcript.class).findAll()) {
            listTranscript.add(transcript);
        }
        return listTranscript;
    }

    public List<Transcript> getListTranscriptByIdStudent(int id) {
        List<Transcript> listTranscript = new ArrayList<>();
        for (Transcript transcript : realm.where(Transcript.class).equalTo("idStudent", id).findAll()) {
            listTranscript.add(transcript);
        }
        return listTranscript;
    }

    public Unit getUnit(int id) {
        Unit object = realm.where(Unit.class).equalTo("id", id).findFirst();
        return realm.copyFromRealm(object);
    }

    public ClassR getClass(int id) {
        ClassR object = realm.where(ClassR.class).equalTo("id", id).findFirst();
        return realm.copyFromRealm(object);
    }

    public Student getStudent(int id) {
        RealmResults<Student> object = realm.where(Student.class).equalTo("id", id).findAll();
        return realm.copyFromRealm(object.first());
    }

    public Transcript getTranscript(int id) {
        Transcript object = realm.where(Transcript.class).equalTo("id", id).findFirst();
        return realm.copyFromRealm(object);
    }*/
}
