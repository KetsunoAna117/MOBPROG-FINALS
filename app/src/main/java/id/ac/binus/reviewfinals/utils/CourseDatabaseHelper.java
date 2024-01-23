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
        // TODO fill logic to insert course here

    }

    public boolean updateCourse(String courseID, String courseName){
        // TODO fill logic to update course here, make sure don't update courseID too

    }

    public boolean deleteCourse(String courseID){
        // TODO fill logic to delete course here

    }

    public Course getCourse(String courseID){
        // TODO add logic to get course by courseID here

    }

    public ArrayList<Course> getAllCourse(){
        // TODO add logic to getAllCourse here

    }
}
