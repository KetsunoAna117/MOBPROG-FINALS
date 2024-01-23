package id.ac.binus.reviewfinals.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import id.ac.binus.reviewfinals.models.Course;

public class CourseDatabaseHelper extends DatabaseHelper{
    private Context context;

    public CourseDatabaseHelper(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public boolean insertCourse(String courseID, String courseName){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("courseID", courseID);
        values.put("courseName", courseName);

        long result = db.insert("course", null, values);
        return result > 0;
    }

    public boolean updateCourse(String courseID, String courseName){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("courseName", courseName);

        long result = db.update("course", values, "courseID = ?", new String[]{courseID});
        return result > 0;

    }

    public boolean deleteCourse(String courseID){
        SQLiteDatabase db = getWritableDatabase();

        long result = db.delete("course", "courseID = ?", new String[]{courseID});
        return result > 0;
    }

    public Course getCourse(String courseID){
        SQLiteDatabase db = getWritableDatabase();
        StudentDatabaseHelper studentDbHelper = new StudentDatabaseHelper(context);

        Cursor cursor = db.rawQuery("SELECT * FROM course WHERE courseID = ?", new String[]{courseID});
        while(cursor.moveToNext()){
            return new Course(cursor.getString(0), cursor.getString(1), studentDbHelper.getAllStudentBasedOnCourseID(courseID));
        }
        cursor.close();

        return null;
    }

    public ArrayList<Course> getAllCourse(){
        SQLiteDatabase db = getWritableDatabase();
        StudentDatabaseHelper studentDbHelper = new StudentDatabaseHelper(context);
        ArrayList<Course> courseList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM course", null);
        while(cursor.moveToNext()){
            courseList.add(new Course(cursor.getString(0), cursor.getString(1), studentDbHelper.getAllStudentBasedOnCourseID(cursor.getString(0))));
        }

        cursor.close();
        return courseList;
    }
}
