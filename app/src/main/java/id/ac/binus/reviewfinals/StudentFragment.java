package id.ac.binus.reviewfinals;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import id.ac.binus.reviewfinals.models.Student;
import id.ac.binus.reviewfinals.utils.DatabaseHelper;
import id.ac.binus.reviewfinals.utils.StudentDatabaseHelper;

public class StudentFragment extends Fragment {
    private ListView fragment_student_listview;
    private ArrayList<Student> studentList;

    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);

        EditText fragment_student_search_edittext = view.findViewById(R.id.fragment_student_search_edittext);
        Button fragment_student_search_button = view.findViewById(R.id.fragment_student_search_button);
        Button fragment_student_add_button = view.findViewById(R.id.fragment_student_add_button);
        fragment_student_listview = view.findViewById(R.id.fragment_student_listview);

        // get all data from database
        putStudentData();

        fragment_student_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = fragment_student_search_edittext.getText().toString();

                if(text.isEmpty()){
                    putStudentData();
                }
                else{
                    ArrayList<Student> filteredStudentList = new ArrayList<>();

                    for(Student s: studentList){
                        if(s.getStudentName().contains(text)){
                            filteredStudentList.add(s);
                        }
                    }
                    StudentAdapter filteredAdapter = new StudentAdapter(getActivity(), R.layout.activity_student_adapter, filteredStudentList);
                    fragment_student_listview.setAdapter(filteredAdapter);
                }
            }
        });

        fragment_student_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertStudentActivity.class);
                startActivity(intent);
            }
        });

        fragment_student_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), StudentDetails.class);

                Student selectedStudent = studentList.get(position);
                intent.putExtra("studentNIM", selectedStudent.getStudentNIM());
                intent.putExtra("studentName", selectedStudent.getStudentName());
                intent.putExtra("studentPhone", selectedStudent.getStudentPhone());
                intent.putExtra("courseID", selectedStudent.getCourseID());
                startActivity(intent);
            }
        });


        return view;
    }

    private void putStudentData() {
        StudentDatabaseHelper db = new StudentDatabaseHelper(getActivity());
        studentList = db.getAllStudent();
        StudentAdapter adapter = new StudentAdapter(getActivity(), R.layout.activity_student_adapter, studentList);
        fragment_student_listview.setAdapter(adapter);
    }
}