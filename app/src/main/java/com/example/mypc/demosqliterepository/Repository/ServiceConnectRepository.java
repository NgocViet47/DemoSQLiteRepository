package com.example.mypc.demosqliterepository.Repository;

import android.content.Context;

import com.example.mypc.demosqliterepository.model.ClassR;
import com.example.mypc.demosqliterepository.model.Student;
import com.example.mypc.demosqliterepository.model.Transcript;
import com.example.mypc.demosqliterepository.model.Unit;
import com.example.mypc.demosqliterepository.service.RealmService;

import java.util.List;

/**
 * Created by MyPC on 5/25/2017.
 */

public class ServiceConnectRepository implements ServiceRepository {

    private RealmService loadService(Context context){
        RealmService service = new RealmService(context);
        //SQLiteService service = new SQLiteService(context);
        return service;
    }
    @Override
    public void addUnit(Context context, Unit t) {
        loadService(context).addUnit(t);
    }

    @Override
    public void addClass(Context context, ClassR t) {
        loadService(context).addClass(t);
    }

    @Override
    public void addStudent(Context context, Student t) {
        loadService(context).addStudent(t);
    }

    @Override
    public void addTranscript(Context context, Transcript t) {
        loadService(context).addTranscript(t);
    }

    @Override
    public List<Unit> getAllUnit(Context context) {
        return loadService(context).getAllUnit();
    }

    @Override
    public List<ClassR> getAllClass(Context context) {
        return loadService(context).getAllClass();
    }

    @Override
    public List<ClassR> getListClassByIdUnit(Context context, int id) {
        return loadService(context).getListClassByIdUnit(id);
    }

    @Override
    public List<Student> getAllStudent(Context context) {
        return loadService(context).getAllStudent();    }

    @Override
    public List<Student> getListStudentByIdClass(Context context, int id) {
        return loadService(context).getListStudentByIdClass(id);
    }

    @Override
    public List<Transcript> getAllTranscript(Context context) {
        return loadService(context).getAllTranscript();    }

    @Override
    public List<Transcript> getListTranscripByIdStudent(Context context, int id) {
        return loadService(context).getListTranscriptByIdStudent(id);
    }

    @Override
    public Unit getUnit(Context context, int id) {
        return loadService(context).getUnit(id);
    }

    @Override
    public ClassR getClass(Context context, int id) {
        return loadService(context).getClass(id);
    }

    @Override
    public Student getStudent(Context context, int id) {
        return loadService(context).getStudent(id);
    }

    @Override
    public Transcript getTranscript(Context context, int id) {
        return loadService(context).getTranscript(id);
    }
}
