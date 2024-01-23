package id.ac.binus.reviewfinals.models;

public class Student {
    private String studentNIM;
    private String studentName;
    private String studentPhone;
    private String courseID;

    public Student(String studentNIM, String studentName, String studentPhone, String courseID) {
        this.studentNIM = studentNIM;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.courseID = courseID;
    }

    public String getStudentNIM() {
        return studentNIM;
    }

    public void setStudentNIM(String studentNIM) {
        this.studentNIM = studentNIM;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
