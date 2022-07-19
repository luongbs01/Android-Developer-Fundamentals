package com.example.studentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<StudentModel> studentList;
    private LayoutInflater inflater;
    private Context context;
    private OnStudentClickListener listener;
    private int position;

    interface OnStudentClickListener {
        void onStudentClick(StudentModel student);
    }

    public StudentAdapter(Context context, OnStudentClickListener listener, List<StudentModel> studentList) {
        this.context = context;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentModel student = studentList.get(position);
        holder.studentId.setText(student.getStudentID());
        holder.name.setText(student.getName());
        holder.email.setText(student.getEmail());
        holder.dob.setText(student.getDoB());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        public final TextView studentId;
        public final TextView name;
        public final TextView email;
        public final TextView dob;
        final StudentAdapter adapter;

        public StudentViewHolder(@NonNull View itemView, StudentAdapter adapter) {
            super(itemView);
            studentId = itemView.findViewById(R.id.studentId);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            dob = itemView.findViewById(R.id.dob);
            this.adapter = adapter;
        }
    }
}
