package id.ac.binus.reviewfinals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import id.ac.binus.reviewfinals.models.Course;
import id.ac.binus.reviewfinals.models.Student;
import id.ac.binus.reviewfinals.utils.CourseDatabaseHelper;
import id.ac.binus.reviewfinals.utils.StudentDatabaseHelper;

public class CourseDetails extends AppCompatActivity {
    private EditText details_course_id, details_course_name;
    private Button update_data_btn, delete_data_btn;
    private ListView course_details_student_list;
    private ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        CourseDatabaseHelper db = new CourseDatabaseHelper(this);
        Course selectedCourse = db.getCourse(getIntent().getStringExtra("courseID"));

        declareComponentsInActivity();
        assignValuesToComponents(selectedCourse);
        update_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateCourseIDFound(db)) updateCourseToDatabase(db);
            }
        });
        delete_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateCourseIDFound(db)) deleteCourseFromDatabase(db);
            }
        });
    }

    private void deleteCourseFromDatabase(CourseDatabaseHelper db_course) {
        String courseID = details_course_id.getText().toString();

        StudentDatabaseHelper db_student = new StudentDatabaseHelper(this);
        for(Student s : studentList){
            db_student.deleteStudent(s.getStudentNIM());
        }

        boolean deleteCourseSuccess = db_course.deleteCourse(courseID);
        if(deleteCourseSuccess){
            Toast.makeText(
                    this,
                    "Course successfully deleted!",
                    Toast.LENGTH_LONG
            ).show();

            Intent intent = new Intent(CourseDetails.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(
                    this,
                    "Course failed to delete!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void updateCourseToDatabase(CourseDatabaseHelper db) {
        String courseID = details_course_id.getText().toString();
        String courseName = details_course_name.getText().toString();

        boolean updateSuccess = db.updateCourse(courseID, courseName);
        if(updateSuccess){
            Toast.makeText(
                    this,
                    "Course successfully updated!",
                    Toast.LENGTH_LONG
            ).show();

            Intent intent = new Intent(CourseDetails.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(
                    this,
                    "Course failed to update!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private boolean validateCourseIDFound(CourseDatabaseHelper db) {
        String courseID = details_course_id.getText().toString();
        Course courseFound = db.getCourse(courseID);

        if(courseFound != null){
            return true;
        }
        Toast.makeText(
                this,
                "CourseID not found!",
                Toast.LENGTH_LONG
        ).show();
        return false;
    }

    private void assignValuesToComponents(Course selectedCourse) {
        details_course_id.setText(selectedCourse.getCourseID());
        details_course_name.setText(selectedCourse.getCourseName());

        StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        studentList = db.getAllStudentBasedOnCourseID(selectedCourse.getCourseID());
        StudentAdapter adapter = new StudentAdapter(this, R.layout.activity_student_adapter, studentList);
        course_details_student_list.setAdapter(adapter);
    }

    private void declareComponentsInActivity() {
        details_course_id = findViewById(R.id.details_course_id);
        details_course_name = findViewById(R.id.details_course_name);
        update_data_btn = findViewById(R.id.update_data_btn);
        delete_data_btn = findViewById(R.id.delete_data_btn);
        course_details_student_list = findViewById(R.id.course_details_student_list);
    }
}