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
import java.util.zip.Inflater;

import id.ac.binus.reviewfinals.models.Student;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;

    public StudentAdapter(@NonNull Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student selectedStudent = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView adapter_student_id = convertView.findViewById(R.id.adapter_student_id);
        TextView adapter_student_name = convertView.findViewById(R.id.adapter_student_name);

        adapter_student_id.setText(selectedStudent.getStudentNIM());
        adapter_student_name.setText((selectedStudent.getStudentName()));

        return convertView;
    }
}