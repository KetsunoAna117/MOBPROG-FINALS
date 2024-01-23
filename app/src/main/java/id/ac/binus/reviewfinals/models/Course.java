package id.ac.binus.reviewfinals.models;

import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private ArrayList<Student> studentList;

    public Course(String courseID, String courseName, ArrayList<Student> studentList) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.studentList = studentList;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
}
