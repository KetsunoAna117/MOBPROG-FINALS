package id.ac.binus.reviewfinals;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.binus.reviewfinals.models.Course;

public class CourseAdapter extends ArrayAdapter<Course> {
    private Context context;
    private int resource;

    public CourseAdapter(@NonNull Context context, int resource, ArrayList<Course> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Course selectedCourse = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView adapter_course_id = convertView.findViewById(R.id.adapter_course_id);
        TextView adapter_course_name = convertView.findViewById(R.id.adapter_course_name);

        adapter_course_id.setText(selectedCourse.getCourseID());
        adapter_course_name.setText(selectedCourse.getCourseName());

        return convertView;
    }
}