package com.example.mypc.demosqliterepository.Repository;

import android.content.Context;

import com.example.mypc.demosqliterepository.model.Unit;
import com.example.mypc.demosqliterepository.model.ClassR;
import com.example.mypc.demosqliterepository.model.Student;
import com.example.mypc.demosqliterepository.model.Transcript;

import java.util.List;

/**
 * Created by MyPC on 5/25/2017.
 */

public interface ServiceRepository {

    void addUnit(Context context, Unit t);

    void addClass(Context context, ClassR t);

    void addStudent(Context context, Student t);

    void addTranscript(Context context, Transcript t);

    List<Unit> getAllUnit(Context context);

    List<ClassR> getAllClass(Context context);

    List<ClassR> getListClassByIdUnit(Context context, int id);

    List<Student> getAllStudent(Context context);

    List<Student> getListStudentByIdClass(Context context,int id);

    List<Transcript> getAllTranscript(Context context);

    List<Transcript> getListTranscripByIdStudent(Context context,int id);

    Unit getUnit(Context context, int id);

    ClassR getClass(Context context, int id);

    Student getStudent(Context context, int id);

    Transcript getTranscript(Context context, int id);
}