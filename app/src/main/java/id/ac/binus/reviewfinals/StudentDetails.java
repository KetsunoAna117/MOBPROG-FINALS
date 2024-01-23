package id.ac.binus.reviewfinals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.binus.reviewfinals.models.Course;
import id.ac.binus.reviewfinals.models.Student;
import id.ac.binus.reviewfinals.utils.CourseDatabaseHelper;
import id.ac.binus.reviewfinals.utils.StudentDatabaseHelper;

public class StudentDetails extends AppCompatActivity {
    private EditText details_student_id, details_student_name, details_student_phone, details_student_course_enrolled_id;
    private TextView details_student_course_enrolled_name;
    private Button update_data_btn, delete_data_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        String studentNIM = getIntent().getStringExtra("studentNIM");
        String studentName = getIntent().getStringExtra("studentName");
        String studentPhone = getIntent().getStringExtra("studentPhone");
        String courseID = getIntent().getStringExtra("courseID");

        details_student_id = findViewById(R.id.details_student_id);
        details_student_name = findViewById(R.id.details_student_name);
        details_student_phone = findViewById(R.id.details_student_phone);
        details_student_course_enrolled_id = findViewById(R.id.details_student_course_enrolled_id);
        details_student_course_enrolled_name = findViewById(R.id.details_student_course_enrolled_name);
        update_data_btn = findViewById(R.id.update_data_btn);
        delete_data_btn = findViewById(R.id.delete_data_btn);

        details_student_id.setText(studentNIM);
        details_student_name.setText(studentName);
        details_student_phone.setText(studentPhone);

        CourseDatabaseHelper db = new CourseDatabaseHelper(this);
        Course selectedCourse = db.getCourse(courseID);
        details_student_course_enrolled_id.setText(selectedCourse.getCourseID());
        details_student_course_enrolled_name.setText(selectedCourse.getCourseName());

        update_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateStudentNimSuccess() && validateCourseIDSuccess()){
                    updateStudentToDatabase();
                }
            }
        });

        delete_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateStudentNimSuccess()){
                    deleteStudentFromDatabase();
                }
            }
        });

    }

    private void deleteStudentFromDatabase() {
        StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        boolean deleteSuccess = db.deleteStudent(details_student_id.getText().toString());

        if(deleteSuccess){
            Toast.makeText(this,
                    "Successfully deleted student data",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(StudentDetails.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,
                    "Failed to delete student data",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void updateStudentToDatabase() {
        StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        boolean updateSuccess = db.updateStudent(
                details_student_id.getText().toString(),
                details_student_name.getText().toString(),
                details_student_phone.getText().toString(),
                details_student_course_enrolled_id.getText().toString());

        if(updateSuccess){
            Toast.makeText(this,
                    "Successfully updated student data",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(StudentDetails.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,
                    "Failed to update student data",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateStudentNimSuccess() {
        StudentDatabaseHelper db = new StudentDatabaseHelper(this);
        Student studentFound = db.getStudentByNIM(details_student_id.getText().toString());

        if(studentFound != null){
            return true;
        }

        Toast.makeText(this,
                String.format("Error student with NIM %s is not exist",details_student_id.getText().toString()),
                Toast.LENGTH_LONG).show();
        return false;
    }

    private boolean validateCourseIDSuccess() {
        CourseDatabaseHelper db = new CourseDatabaseHelper(this);
        Course courseFound = db.getCourse(details_student_course_enrolled_id.getText().toString());

        if(courseFound != null){
            return true;
        }

        Toast.makeText(this,
                "Please enter valid courseID",
                Toast.LENGTH_LONG).show();
        return false;
    }
}