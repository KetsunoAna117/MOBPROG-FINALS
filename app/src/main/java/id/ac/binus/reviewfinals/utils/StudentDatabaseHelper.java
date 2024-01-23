package id.ac.binus.reviewfinals.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import id.ac.binus.reviewfinals.models.Student;

public class StudentDatabaseHelper extends DatabaseHelper{
    private Context context;
    public StudentDatabaseHelper(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public boolean insertStudent(String studentNim, String studentName, String studentPhone, String courseID){
        // TODO add logic to insert student here

    }

    public boolean updateStudent(String studentNim, String studentName, String studentPhone, String courseID){
        // TODO add logic to update student here, make sure to not update studentNIM when updating data

    }

    public boolean deleteStudent(String studentNim){
        // TODO add logic to deleteStudent here

    }

    public Student getStudentByNIM(String studentNim){
        // TODO add logic to get student by nim here

    }

    public ArrayList<Student> getAllStudent(){
        // TODO add logic to get all student here

    }


    public ArrayList<Student> getAllStudentBasedOnCourseID(String courseID){\
        // TODO add logic get all student based on course here

    }
}
