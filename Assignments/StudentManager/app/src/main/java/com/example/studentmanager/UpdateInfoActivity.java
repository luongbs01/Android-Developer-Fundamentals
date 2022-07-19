package com.example.studentmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UpdateInfoActivity extends AppCompatActivity {

    DatabaseHandler db;

    private Toolbar toolbar;
    private EditText editId;
    private EditText editName;
    private EditText editEmail;
    private EditText editDob;
    private Button update;

    private String studentID;
    private String studentName;
    private String studentEmail;
    private String DoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        db = new DatabaseHandler(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editId = findViewById(R.id.edit_id);
        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editDob = findViewById(R.id.edit_dob);
        update = findViewById(R.id.update);

        try {
            Intent intent = getIntent();
            studentID = intent.getStringExtra("id");
            studentName = intent.getStringExtra("name");
            studentEmail = intent.getStringExtra("email");
            DoB = intent.getStringExtra("dob");

            editId.setText(studentID);
            editName.setText(studentName);
            editEmail.setText(studentEmail);
            editDob.setText(DoB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateInfoActivity.this);
                builder.setTitle("Are you sure?");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        studentID = editId.getText().toString();
                        studentName = editName.getText().toString();
                        studentEmail = editEmail.getText().toString();
                        DoB = editDob.getText().toString();

                        db.updateStudent(new StudentModel(studentID, studentName, studentEmail, DoB));
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
    }
}