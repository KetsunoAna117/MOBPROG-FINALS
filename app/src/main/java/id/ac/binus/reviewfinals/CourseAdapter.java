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
        /*
        TODO add logic to show course data here
        Required Field:
        1. adapter_course_id (TextView)
        2. adapter_course_name (TextView)
        For references open layour activity_course_adapter
         */


    }
}