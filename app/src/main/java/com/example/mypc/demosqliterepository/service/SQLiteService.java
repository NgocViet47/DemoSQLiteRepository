package com.example.mypc.demosqliterepository.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mypc.demosqliterepository.model.ClassR;
import com.example.mypc.demosqliterepository.model.Student;
import com.example.mypc.demosqliterepository.model.Transcript;
import com.example.mypc.demosqliterepository.model.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 5/25/2017.
 */

public class SQLiteService extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "databaseStudent";
    private static final String TABLE_UNIT = "tableUnit";
    private static final String TABLE_CLASS = "tableClass";
    private static final String TABLE_STUDENT = "tableStudent";
    private static final String TABLE_TRANSCRIPT = "tableTranscripts";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ID_UNIT = "idUnit";
    private static final String COLUMN_ID_CLASS = "idClass";
    private static final String COLUMN_ID_STUDENT = "idStudent";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BIRTH_DATE = "birthDate";
    private static final String COLUMN_PIONT_15 = "piont15";
    private static final String COLUMN_PIONT_45 = "piont45";
    private static final String COLUMN_ADDRESS = "address";

    public interface STUDENT_COLUMN{
        int ID = 0;
        int NAME = 1;
        int ID_UNIT = 2;
        int ID_CLASS = 3;
        int BIRTH_DATE = 4;
        int ADDRESS = 5;
    }

    public interface CLASS_COLUMN{
        int ID = 0;
        int NAME = 1;
        int ID_UNIT = 2;
    }

    public interface UNIT_COLUMN{
        int ID = 0;
        int NAME = 1;
    }

    public interface TRANSCRIPT_COLUMN{
        int ID = 0;
        int NAME = 1;
        int ID_UNIT = 3;
        int ID_CLASS = 4;
        int ID_STUDENT = 2;
        int POINT15 = 6;
        int POINT45 = 5;
    }

    private static final String DATABASE_ALTER_TEAM_1 = "ALTER TABLE "
            + TABLE_STUDENT + " ADD COLUMN " + COLUMN_ADDRESS + " TEXT;";

    public SQLiteService(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String scriptUnit = "CREATE TABLE " + TABLE_UNIT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT"
                + ")";
        String scriptClass = "CREATE TABLE " + TABLE_CLASS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ID_UNIT + " INTEGER"
                + ")";
        String scriptStudent = "CREATE TABLE " + TABLE_STUDENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ID_UNIT + " INTEGER,"
                + COLUMN_ID_CLASS + " INTEGER,"
                + COLUMN_BIRTH_DATE + " TEXT"
                +","+ COLUMN_ADDRESS + " TEXT"
                + ")";
        String scriptTranscript = "CREATE TABLE " + TABLE_TRANSCRIPT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ID_STUDENT + " INTEGER,"
                + COLUMN_ID_UNIT + " INTEGER,"
                + COLUMN_ID_CLASS + " INTEGER,"
                + COLUMN_PIONT_45 + " INTEGER,"
                + COLUMN_PIONT_15 + " INTEGER"
                + ")";
        db.execSQL(scriptUnit);
        db.execSQL(scriptClass);
        db.execSQL(scriptStudent);
        db.execSQL(scriptTranscript);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 2:
                db.execSQL(DATABASE_ALTER_TEAM_1);
                break;
            case 3:
                db.execSQL(DATABASE_ALTER_TEAM_1);
                break;

        }
    }
    public void addUnit(Unit unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, unit.getName());

        db.insert(TABLE_UNIT, null, values);
        db.close();
    }
    public void addClass(ClassR mClass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,mClass.getName());
        values.put(COLUMN_ID_UNIT,mClass.getIdUnit());

        db.insert(TABLE_CLASS,null,values);
        db.close();
    }
    public void addStudent(Student mStudent){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,mStudent.getName());
        values.put(COLUMN_ID_UNIT,mStudent.getIdUnit());
        values.put(COLUMN_ID_CLASS,mStudent.getIdClass());
        values.put(COLUMN_BIRTH_DATE,mStudent.getBirthDate());
        values.put(COLUMN_ADDRESS,"HANOI");

        db.insert(TABLE_STUDENT,null,values);
        db.close();
    }
    public void addTranscript(Transcript mTranscript){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,mTranscript.getName());
        values.put(COLUMN_ID_UNIT,mTranscript.getIdUnit());
        values.put(COLUMN_ID_CLASS,mTranscript.getIdClass());
        values.put(COLUMN_ID_STUDENT,mTranscript.getIdStudent());
        values.put(COLUMN_PIONT_15,mTranscript.getPoint15());
        values.put(COLUMN_PIONT_45,mTranscript.getPoint45());

        db.insert(TABLE_TRANSCRIPT,null,values);
        db.close();
    }
    public List<Unit> getAllUnit() {
        List<Unit> unitList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_UNIT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Unit mUnit = new Unit();
                mUnit.setId(Integer.parseInt(cursor.getString(UNIT_COLUMN.ID)));
                mUnit.setName(cursor.getString(UNIT_COLUMN.NAME));
                unitList.add(mUnit);
            } while (cursor.moveToNext());
        }
        return unitList;
    }
    public List<ClassR> getAllClass(){
        List<ClassR> classList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLASS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ClassR mClass = new ClassR();
                mClass.setId(Integer.parseInt(cursor.getString(CLASS_COLUMN.ID)));
                mClass.setName(cursor.getString(CLASS_COLUMN.NAME));
                mClass.setIdUnit(Integer.parseInt(cursor.getString(CLASS_COLUMN.ID_UNIT)));
                classList.add(mClass);
            } while (cursor.moveToNext());
        }
        return classList;
    }
    public List<ClassR> getListClassByIdUnit(int id){
        List<ClassR> classList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CLASS,
                new String[] { COLUMN_ID, COLUMN_NAME,COLUMN_ID_UNIT},
                COLUMN_ID_UNIT + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ClassR mClass = new ClassR();
                mClass.setId(Integer.parseInt(cursor.getString(CLASS_COLUMN.ID)));
                mClass.setName(cursor.getString(CLASS_COLUMN.NAME));
                mClass.setIdUnit(Integer.parseInt(cursor.getString(CLASS_COLUMN.ID_UNIT)));
                classList.add(mClass);
            } while (cursor.moveToNext());
        }
        return classList;
    }
    public List<Student> getAllStudent(){
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student mStudent = new Student();
                mStudent.setId(Integer.parseInt(cursor.getString(STUDENT_COLUMN.ID)));
                mStudent.setName(cursor.getString(STUDENT_COLUMN.NAME));
                mStudent.setIdUnit(Integer.parseInt(cursor.getString(STUDENT_COLUMN.ID_UNIT)));
                mStudent.setIdClass(Integer.parseInt(cursor.getString(STUDENT_COLUMN.ID_CLASS)));
                mStudent.setBirthDate(cursor.getString(STUDENT_COLUMN.BIRTH_DATE));
                mStudent.setAddress(cursor.getString(STUDENT_COLUMN.ADDRESS));

                studentList.add(mStudent);
            } while (cursor.moveToNext());
        }
        return studentList;
    }
    public List<Student> getListStudentByIdClass(int id){
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT,
                new String[] { COLUMN_ID, COLUMN_NAME,COLUMN_ID_UNIT,COLUMN_ID_CLASS,COLUMN_BIRTH_DATE,COLUMN_ADDRESS},
                COLUMN_ID_CLASS + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Student mStudent = new Student();
                mStudent.setId(Integer.parseInt(cursor.getString(STUDENT_COLUMN.ID)));
                mStudent.setName(cursor.getString(STUDENT_COLUMN.NAME));
                mStudent.setIdUnit(Integer.parseInt(cursor.getString(STUDENT_COLUMN.ID_UNIT)));
                mStudent.setIdClass(Integer.parseInt(cursor.getString(STUDENT_COLUMN.ID_CLASS)));
                mStudent.setBirthDate(cursor.getString(STUDENT_COLUMN.BIRTH_DATE));
                mStudent.setAddress(cursor.getString(STUDENT_COLUMN.ADDRESS));
                studentList.add(mStudent);
            } while (cursor.moveToNext());
        }
        return studentList;
    }
    public List<Transcript> getAllTranscript(){
        List<Transcript> transcriptList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSCRIPT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Transcript mTranscript = new Transcript();
                mTranscript.setId(cursor.getInt(TRANSCRIPT_COLUMN.ID));
                mTranscript.setName(cursor.getString(TRANSCRIPT_COLUMN.NAME));
                mTranscript.setIdUnit(cursor.getInt(TRANSCRIPT_COLUMN.ID_UNIT));
                mTranscript.setIdClass(cursor.getInt(TRANSCRIPT_COLUMN.ID_CLASS));
                mTranscript.setIdStudent(cursor.getInt(TRANSCRIPT_COLUMN.ID_STUDENT));
                mTranscript.setPoint15(cursor.getInt(TRANSCRIPT_COLUMN.POINT15));
                mTranscript.setPoint45(cursor.getInt(TRANSCRIPT_COLUMN.POINT45));
                transcriptList.add(mTranscript);
            } while (cursor.moveToNext());
        }
        return transcriptList;
    }
    public List<Transcript> getListTranscriptByIdStudent(int id){
        List<Transcript> transcriptList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT,
                new String[] { COLUMN_ID, COLUMN_NAME,COLUMN_ID_UNIT,COLUMN_ID_CLASS,COLUMN_BIRTH_DATE},
                COLUMN_ID_STUDENT + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Transcript mTranscript = new Transcript();
                mTranscript.setId(cursor.getInt(TRANSCRIPT_COLUMN.ID));
                mTranscript.setName(cursor.getString(TRANSCRIPT_COLUMN.NAME));
                mTranscript.setIdUnit(cursor.getInt(TRANSCRIPT_COLUMN.ID_UNIT));
                mTranscript.setIdClass(cursor.getInt(TRANSCRIPT_COLUMN.ID_CLASS));
                mTranscript.setIdStudent(cursor.getInt(TRANSCRIPT_COLUMN.ID_STUDENT));
                mTranscript.setPoint15(cursor.getInt(TRANSCRIPT_COLUMN.POINT15));
                mTranscript.setPoint45(cursor.getInt(TRANSCRIPT_COLUMN.POINT45));
                transcriptList.add(mTranscript);
            } while (cursor.moveToNext());
        }
        return transcriptList;
    }
    public Unit getUnit(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_UNIT,
                new String[] { COLUMN_ID, COLUMN_NAME},
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Unit mUnit = new Unit(cursor.getInt(UNIT_COLUMN.ID),
                cursor.getString(UNIT_COLUMN.NAME));
        cursor.close();
        db.close();
        return mUnit;
    }
    public ClassR getClass(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLASS,
                new String[] { COLUMN_ID, COLUMN_NAME,COLUMN_ID_UNIT},
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ClassR mClass = new ClassR(cursor.getInt(CLASS_COLUMN.ID),
                cursor.getString(CLASS_COLUMN.NAME),
                cursor.getInt(CLASS_COLUMN.ID_UNIT));
        cursor.close();
        db.close();
        return mClass;
    }

    public Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENT,
                new String[] { COLUMN_ID, COLUMN_NAME,COLUMN_ID_UNIT,COLUMN_ID_CLASS,COLUMN_BIRTH_DATE},
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Student mStudent = new Student(cursor.getInt(STUDENT_COLUMN.ID),
                cursor.getString(STUDENT_COLUMN.NAME),
                cursor.getInt(STUDENT_COLUMN.ID_UNIT),
                cursor.getInt(STUDENT_COLUMN.ID_CLASS),
                cursor.getString(STUDENT_COLUMN.BIRTH_DATE)
                ,cursor.getString(STUDENT_COLUMN.ADDRESS)
        );
        cursor.close();
        db.close();
        return mStudent;
    }
    public Transcript getTranscript(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TRANSCRIPT,
                new String[] { COLUMN_ID, COLUMN_NAME,COLUMN_ID_UNIT,
                        COLUMN_ID_CLASS,COLUMN_ID_STUDENT,
                        COLUMN_PIONT_15,COLUMN_PIONT_45},
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Transcript mTrascript = new Transcript(cursor.getInt(TRANSCRIPT_COLUMN.ID),
                cursor.getString(TRANSCRIPT_COLUMN.NAME),
                cursor.getInt(TRANSCRIPT_COLUMN.ID_UNIT),
                cursor.getInt(TRANSCRIPT_COLUMN.ID_CLASS),
                cursor.getInt(TRANSCRIPT_COLUMN.ID_STUDENT),
                cursor.getInt(TRANSCRIPT_COLUMN.POINT15),
                cursor.getInt(TRANSCRIPT_COLUMN.POINT45)
                );
        cursor.close();
        db.close();
        return mTrascript;
    }

}
