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

import id.ac.binus.reviewfinals.models.Course;
import id.ac.binus.reviewfinals.utils.CourseDatabaseHelper;

public class CourseFragment extends Fragment {
    private ArrayList<Course> courseList;
    private ListView fragment_course_listview;
    private Button fragment_course_add_button, fragment_course_search_button;
    private EditText fragment_course_search_textbox;
    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        fragment_course_listview = view.findViewById(R.id.fragment_course_listview);
        fragment_course_add_button = view.findViewById(R.id.fragment_course_add_button);
        fragment_course_search_button = view.findViewById(R.id.fragment_course_search_button);
        fragment_course_search_textbox = view.findViewById(R.id.fragment_course_search_textbox);

        putCourseData();

        fragment_course_add_button.setOnClickListener(event -> {
            Intent intent = new Intent(getActivity(), InsertCourseActivity.class);
            startActivity(intent);
        });

        fragment_course_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment_course_search_textbox.getText().toString().isEmpty()){
                    putCourseData();
                }
                else{
                    ArrayList<Course> filteredCourse = new ArrayList<>();
                    for(Course c : courseList){
                        String searchedCourseName = fragment_course_search_textbox.getText().toString();
                        if(c.getCourseName().contains(searchedCourseName)){
                            filteredCourse.add(c);
                        }
                    }

                    CourseAdapter adapter = new CourseAdapter(getActivity(), R.layout.activity_course_adapter, filteredCourse);
                    fragment_course_listview.setAdapter(adapter);
                }
            }
        });

        fragment_course_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = courseList.get(position);
                Intent intent = new Intent(getActivity(), CourseDetails.class);

                intent.putExtra("courseID", selectedCourse.getCourseID());
                startActivity(intent);
            }
        });

        return view;
    }

    private void putCourseData() {
        CourseDatabaseHelper db = new CourseDatabaseHelper(getActivity());
        courseList = db.getAllCourse();
        CourseAdapter adapter = new CourseAdapter(getActivity(), R.layout.activity_course_adapter, courseList);
        fragment_course_listview.setAdapter(adapter);
    }
}