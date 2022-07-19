package com.example.studentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_DOB = "dob";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_EMAIL, KEY_DOB);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    public void addStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, student.getStudentID());
        values.put(KEY_NAME, student.getName());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_DOB, student.getDoB());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public StudentModel getStudent(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[]{String.valueOf(studentId)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        StudentModel student = new StudentModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return student;
    }

    public List<StudentModel> getAllStudents() {
        List<StudentModel> studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            StudentModel student = new StudentModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            studentList.add(student);
            cursor.moveToNext();
        }
        return studentList;
    }

    public void updateStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, student.getStudentID());
        values.put(KEY_NAME, student.getName());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_DOB, student.getDoB());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(student.getStudentID())});
        db.close();
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(studentId)});
        db.close();
    }
}
