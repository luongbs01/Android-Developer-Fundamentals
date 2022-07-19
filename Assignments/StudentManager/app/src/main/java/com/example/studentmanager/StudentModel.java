package com.example.studentmanager;

public class StudentModel {
    private String studentID;
    private String name;
    private String email;
    private String DoB;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public StudentModel(String studentID, String name, String email, String doB) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.DoB = doB;
    }
}
