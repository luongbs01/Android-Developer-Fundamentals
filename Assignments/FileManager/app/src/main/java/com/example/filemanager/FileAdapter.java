package com.example.filemanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.LinkedList;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    private final LinkedList<File> fileList;
    private LayoutInflater inflater;
    private Context context;

    public FileAdapter(Context context, LinkedList<File> fileList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.file_item, parent, false);
        return new FileViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        File file = fileList.get(position);
        holder.name.setText(file.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (file.isFile()) {
                    Uri uri = Uri.parse("content://" + file.getAbsolutePath());
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    if (getFileExtension(file).equals(".txt")) {
                        intent.setType("text/*");
                    } else if (getFileExtension(file).equals(".jpg") || getFileExtension(file).equals(".png")) {
                        intent.setType("image/*");
                    }
                    intent.setData(uri);
                    context.startActivity(Intent.createChooser(intent, "Open file"));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    class FileViewHolder extends RecyclerView.ViewHolder {

        public final TextView name;
        final FileAdapter adapter;

        public FileViewHolder(@NonNull View itemView, FileAdapter adapter) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            this.adapter = adapter;
        }
    }
}
