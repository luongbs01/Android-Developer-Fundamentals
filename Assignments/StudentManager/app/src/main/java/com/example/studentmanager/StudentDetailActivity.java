package com.example.studentmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StudentDetailActivity extends AppCompatActivity {

    DatabaseHandler db;

    private Toolbar toolbar;
    private TextView studentId;
    private TextView name;
    private TextView email;
    private TextView dob;
    private Button edit;
    private Button delete;

    private String studentID;
    private String studentName;
    private String studentEmail;
    private String DoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        db = new DatabaseHandler(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        studentId = findViewById(R.id.studentId);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentDetailActivity.this);
                builder.setTitle("Are you sure?");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteStudent(studentID);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentDetailActivity.this, UpdateInfoActivity.class);
                intent.putExtra("id", studentID);
                intent.putExtra("name", studentName);
                intent.putExtra("email", studentEmail);
                intent.putExtra("dob", DoB);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        studentID = intent.getStringExtra("id");
        studentName = intent.getStringExtra("name");
        studentEmail = intent.getStringExtra("email");
        DoB = intent.getStringExtra("dob");

        studentId.setText(studentID);
        name.setText(studentName);
        email.setText(studentEmail);
        dob.setText(DoB);
    }
}