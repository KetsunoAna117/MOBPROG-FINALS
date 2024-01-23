package id.ac.binus.reviewfinals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import id.ac.binus.reviewfinals.models.Course;
import id.ac.binus.reviewfinals.models.Student;
import id.ac.binus.reviewfinals.utils.CourseDatabaseHelper;
import id.ac.binus.reviewfinals.utils.StudentDatabaseHelper;

public class InsertStudentActivity extends AppCompatActivity {
    private EditText student_id_edit_text, student_name_edit_text, student_phone_edit_text, student_course_enrolled_edit_text;
    private Button submit_student;
    private ListView course_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_student);

        student_id_edit_text = findViewById(R.id.student_id_edit_text);
        student_name_edit_text = findViewById(R.id.student_name_edit_text);
        student_phone_edit_text = findViewById(R.id.student_phone_edit_text);
        student_course_enrolled_edit_text = findViewById(R.id.student_course_enrolled_edit_text);
        submit_student = findViewById(R.id.submit_student);
        course_list_view = findViewById(R.id.course_list_view);

        submit_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateStudentNimUnique() && validateCourseIDSuccess()){
                    insertToDatabase();
                }

            }
        });

        CourseDatabaseHelper db = new CourseDatabaseHelper(this);
        ArrayList<Course> courseList = db.getAllCourse();
        CourseAdapter adapter = new CourseAdapter(this, R.layout.activity_course_adapter, courseList);
        course_list_view.setAdapter(adapter);


    }

    private boolean validateCourseIDSuccess() {
        CourseDatabaseHelper db = new CourseDatabaseHelper(this);
        Course courseFound = db.getCourse(student_course_enrolled_edit_text.getText().toString());

        if(courseFound != null){
            return true;
        }

        Toast.makeText(this,
                "Please enter valid courseID",
                Toast.LENGTH_LONG).show();
        return false;
    }

    private void insertToDatabase() {
        StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        boolean insertSucesss = db.insertStudent(student_id_edit_text.getText().toString(),
                student_name_edit_text.getText().toString(),
                student_phone_edit_text.getText().toString(),
                student_course_enrolled_edit_text.getText().toString());

        if(insertSucesss){
            Toast.makeText(this,
                    "Student Successfully inputted!",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(InsertStudentActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,
                    "Student failed to be inserted",
                    Toast.LENGTH_LONG).show();
        }

    }


    private boolean validateStudentNimUnique() {
        StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        Student studentFound = db.getStudentByNIM(student_id_edit_text.getText().toString());

        if(studentFound == null){
            return true;
        }

        Toast.makeText(this,
                String.format("Error student with NIM %s is already exist",student_id_edit_text.getText().toString()),
                Toast.LENGTH_LONG).show();
        return false;
    }
}