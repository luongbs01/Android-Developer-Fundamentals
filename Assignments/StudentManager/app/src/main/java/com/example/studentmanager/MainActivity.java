package com.example.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnStudentClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    DatabaseHandler db;
    private List<StudentModel> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
//        db.addStudent(new StudentModel("20194108", "Hoang Minh Luong", "luong.hm194108@sis.hust.edu.vn", "19/06/2001"));
//        db.addStudent(new StudentModel("20194109", "Hoang Minh Luong2", "luong2.hm194108@sis.hust.edu.vn", "19/06/2001"));
//        db.addStudent(new StudentModel("20194110", "Hoang Minh Luong3", "luong3.hm194108@sis.hust.edu.vn", "19/06/2001"));
//        db.addStudent(new StudentModel("20194111", "Hoang Minh Luong4", "luong4.hm194108@sis.hust.edu.vn", "19/06/2001"));
//        db.addStudent(new StudentModel("20194113", "Hoang Minh Luong6", "luong6.hm194108@sis.hust.edu.vn", "19/06/2001"));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        studentList = db.getAllStudents();
        recyclerView.setAdapter(new StudentAdapter(MainActivity.this, MainActivity.this, studentList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new_student) {

        }
        return true;
    }

    @Override
    public void onStudentClick(StudentModel student) {
        Intent intent = new Intent(this, StudentDetailActivity.class);
        intent.putExtra("id", student.getStudentID());
        intent.putExtra("name", student.getName());
        intent.putExtra("email", student.getEmail());
        intent.putExtra("dob", student.getDoB());
        startActivity(intent);
    }
}