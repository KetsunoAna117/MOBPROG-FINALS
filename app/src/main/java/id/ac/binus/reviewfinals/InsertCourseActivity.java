package id.ac.binus.reviewfinals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.binus.reviewfinals.models.Course;
import id.ac.binus.reviewfinals.utils.CourseDatabaseHelper;

public class InsertCourseActivity extends AppCompatActivity {
    private EditText course_id_edit_text, course_name_edit_text;
    private Button submit_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_course);

        course_id_edit_text = findViewById(R.id.course_id_edit_text);
        course_name_edit_text = findViewById(R.id.course_name_edit_text);
        submit_course = findViewById(R.id.submit_course);

        submit_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateCourseIDUnique()){
                    insertCourseToDatabase();
                }
            }
        });
    }

    private void insertCourseToDatabase() {
        CourseDatabaseHelper db = new CourseDatabaseHelper(this);
        boolean insertSuccess = db.insertCourse(
                course_id_edit_text.getText().toString(),
                course_name_edit_text.getText().toString()
        );

        if(insertSuccess){
            Toast.makeText(
                    this,
                    "Insert course success",
                    Toast.LENGTH_LONG
            ).show();

            Intent intent = new Intent(InsertCourseActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(
                    this,
                    "Insert course failed",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private boolean validateCourseIDUnique() {
        CourseDatabaseHelper db = new CourseDatabaseHelper(this);
        Course courseFound = db.getCourse(course_id_edit_text.getText().toString());

        if(courseFound == null){
            return true;
        }

        Toast.makeText(this,
                "Please enter another unique courseID",
                Toast.LENGTH_LONG).show();
        return false;
    }
}