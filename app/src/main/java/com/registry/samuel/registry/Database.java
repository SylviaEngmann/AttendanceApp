package com.registry.samuel.registry;

/**
 * Created by samuel on 11/28/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "registry.db";
    public static final String STUDENT_TABLE = "student_table";
    public static final String COURSE_TABLE = "course_table";
    public static final String ATTENDANCE_TABLE = "attendance_table";
    public static final String STUDENT_COURSE_TABLE = "student_course_table";

    //for user able
    public static final String uid = "uid";
    public static final String user_name = "user_name";
    public static final String password = "password";
    public static final String type = "type";

    //for student table
    public static final String sid = "sid";
    public static final String student_name = "student_name";
    public static final String student_id = "student_id";
    public static final String course_id = "course_id";

    //for course table
    public static final String cid = "cid";
    public static final String course_name = "course_name";

    //for student_course table
    public static final String scid = "scid";
    public static final String stud_id = "stud_id";
    public static final String cour_id = "cour_id";

    //for attendance table
    public static final String attend_id = "attend_id";
    public static final String c_id = "c_id";
    public static final String s_id = "s_id";
    public static final String date = "date";
    public static final String absent = "absent";

    //Database variables
    public SQLiteDatabase db;
    public ContentValues cv;

    /**
     * @param context
     */
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + STUDENT_TABLE + "(sid INTEGER PRIMARY KEY AUTOINCREMENT, student_name TEXT, student_id INTEGER, course_id INTEGER)");
        sqLiteDatabase.execSQL("create table " + COURSE_TABLE + "(cid INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT)");
        sqLiteDatabase.execSQL("create table " + ATTENDANCE_TABLE + "(attend_id INTEGER PRIMARY KEY AUTOINCREMENT, c_id INTEGER, s_id INTEGER, date TEXT, absent INTEGER)");
        sqLiteDatabase.execSQL("create table " + STUDENT_COURSE_TABLE + "(scid INTEGER PRIMARY KEY AUTOINCREMENT, stud_id INTEGER, cour_id INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("create table " + STUDENT_TABLE + "(sid INTEGER PRIMARY KEY AUTOINCREMENT, student_name TEXT, student_id INTEGER, course_id INTEGER)");
        sqLiteDatabase.execSQL("create table " + COURSE_TABLE + "(cid INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT)");
        sqLiteDatabase.execSQL("create table " + ATTENDANCE_TABLE + "(attend_id INTEGER PRIMARY KEY AUTOINCREMENT, c_id INTEGER, s_id INTEGER, date TEXT, absent INTEGER)");
        sqLiteDatabase.execSQL("create table " + STUDENT_COURSE_TABLE + "(scid INTEGER PRIMARY KEY AUTOINCREMENT, stud_id INTEGER, cour_id INTEGER)");
    }

    public boolean addCourse(String course) {
        db = this.getWritableDatabase();
        cv = new ContentValues();

        cv.put(course_name, course);

        long result = db.insert(COURSE_TABLE, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    private boolean addStudentCourse(String std_id, String c_id) {
        db = this.getWritableDatabase();
        cv = new ContentValues();

        cv.put(stud_id, std_id);
        cv.put(cour_id, c_id);

        long result = db.insert(STUDENT_COURSE_TABLE, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addStudent(String sname, String stud_id, String co_id) {
        db = this.getWritableDatabase();
        cv = new ContentValues();

        cv.put(student_name, sname);
        cv.put(student_id, stud_id);
        cv.put(course_id, co_id);

        long result = db.insert(STUDENT_TABLE, null, cv);


        if (result == -1) {
            return false;
        } else {
            addStudentCourse(stud_id, co_id);
            return true;
        }
    }

//    public getStudentAttendance(String name) {
//        db = this.getWritableDatabase();
//        cv = new ContentValues();
//
//        cv.put(student_name, name);
//
//        long result = db.raw
//    }
}

//public }
