package id.ac.binus.reviewfinals.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import id.ac.binus.reviewfinals.models.Course;
import id.ac.binus.reviewfinals.models.Student;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "MyDatabase", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE course (" +
                        "courseID TEXT PRIMARY KEY," +
                        "courseName TEXT" +
                        ")"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE student (" +
                        "studentNim TEXT PRIMARY KEY," +
                        "studentName TEXT," +
                        "studentPhone TEXT," +
                        "courseID TEXT," +
                        "FOREIGN KEY (courseID) REFERENCES course(courseID)" +
                        ")");

        sqLiteDatabase.execSQL(
                "INSERT INTO course VALUES ('1', 'Mobile Programming');"
        );

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
