package com.example.filemanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements FileAdapter.OnFileClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FileAdapter adapter;

    private LinkedList<File> fileList = new LinkedList();
    private Stack<File> fileStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileStack.pop();
                processNavigation(fileStack.peek());
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.v("TAG", "sdPath: " + sdPath);
        fileStack.add(Environment.getExternalStorageDirectory());

//        try {
//            String path = sdPath + "/test_read.txt";
//            File file = new File(path);
//            FileInputStream fis = new FileInputStream(file);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//            String line = reader.readLine();
//            reader.close();
//
//            Log.v("TAG", "First line: " + line);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

//        try {
//            String path = sdPath + "/test_write.txt";
//            File file = new File(path);
//            FileOutputStream fos = new FileOutputStream(file);
//            OutputStreamWriter writer = new OutputStreamWriter(fos);
//            writer.write("Thu xem co ghi duoc vao file khong?");
//            writer.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission denied.");
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
            } else {
                Log.v("TAG", "Permission granted.");
            }
        }

        if (Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
            Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
            startActivity(intent);
        }

        if (Build.VERSION.SDK_INT >= 19) {
            File[] files = getExternalFilesDirs(null);
            try {
                for (File f : files) {
                    String path = f.getAbsolutePath();
                    Log.v("TAG", path);
                    if (!path.startsWith("/storage/emulated/0/")) {
//                        String filePath = path.substring(0, path.indexOf("/Android")) + "/test_read.txt"; // /storage/0B07-3205/test_read.txt
//                        Log.v("TAG", "file path: " + filePath);
//                        try {
//                            File file = new File(filePath);
//                            FileInputStream fis = new FileInputStream(file);
//                            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
//                            String line = reader.readLine();
//                            reader.close();
//
//                            Log.v("TAG", "First line (physical sd): " + line);
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        getAllFiles(sdPath);

        adapter = new FileAdapter(this, this, fileList);
        recyclerView.setAdapter(adapter);

        registerForContextMenu(recyclerView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = adapter.getPosition();
        } catch (Exception e) {
            return super.onContextItemSelected(item);
        }
        Log.v("TAG", "Positon: " + position);
        int id = item.getItemId();
        if (id == R.id.action_rename) {
            Log.v("TAG", "Floating context menu: Action rename");
        } else if (id == R.id.action_delete) {
            Log.v("TAG", "Floating context menu: Action delete");
        } else if (id == R.id.action_copy) {
            Log.v("TAG", "Floating context menu: Action copy");
        } else if (id == R.id.action_cut) {
            Log.v("TAG", "Floating context menu: Action cut");
        }

        return super.onContextItemSelected(item);
    }

    private void getAllFiles(String path) {
        File file = new File(path);
        fileList = new LinkedList<>();
        File[] files = file.listFiles();
        try {
            Collections.addAll(fileList, files);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission granted.");
            } else {
                Log.v("TAG", "Permission denied.");
            }
        }
    }

    @Override
    public void onFileClick(File file) {
        fileStack.push(file);
        processNavigation(file);
    }


    private void processNavigation(File file) {
        getAllFiles(file.getAbsolutePath());
        adapter = new FileAdapter(MainActivity.this, MainActivity.this, fileList);
        recyclerView.setAdapter(adapter);
        Log.d("luong", "" + fileStack);
        if (fileStack.size() == 1) {
            toolbar.setNavigationIcon(null);
            toolbar.setTitle("File Manager");
        } else {
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
            toolbar.setTitle(file.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new_file) {
            Log.v("TAG", "Option menu: Action download");
        } else if (id == R.id.action_new_folder) {
            Log.v("TAG", "Option menu: Action share");
        }

        return true;
    }

}