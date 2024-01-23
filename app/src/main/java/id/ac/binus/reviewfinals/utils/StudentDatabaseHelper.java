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
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("studentNim", studentNim);
        values.put("studentName", studentName);
        values.put("studentPhone", studentPhone);
        values.put("courseID", courseID);

        long result = db.insert("student", null, values);
        return result > 0;
    }

    public boolean updateStudent(String studentNim, String studentName, String studentPhone, String courseID){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("studentName", studentName);
        values.put("studentPhone", studentPhone);
        values.put("courseID", courseID);

        long result = db.update("student", values, "studentNim = ?", new String[]{studentNim});
        return result > 0;
    }

    public boolean deleteStudent(String studentNim){
        SQLiteDatabase db = getWritableDatabase();

        long result = db.delete("student", "studentNim = ?", new String[]{studentNim});
        return result > 0;
    }

    public Student getStudentByNIM(String studentNim){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM student WHERE studentNim = ?", new String[]{studentNim});
        while(cursor.moveToNext()){
            return new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }

        return null;
    }

    public ArrayList<Student> getAllStudent(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Student> studentList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM student", null);
        while(cursor.moveToNext()){
            studentList.add(new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();

        return studentList;
    }


    public ArrayList<Student> getAllStudentBasedOnCourseID(String courseID){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Student> studentList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM student WHERE courseID = ?", new String[]{courseID});

        while(cursor.moveToNext()){
            studentList.add(new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();

        return studentList;
    }
}
