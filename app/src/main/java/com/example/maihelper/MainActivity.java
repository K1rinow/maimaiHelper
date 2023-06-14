package com.example.maihelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maihelper.adaoter.SongAdapter;
import com.example.maihelper.bean.Song;

import java.util.ArrayList;

import com.example.maihelper.libraries.GetRecordsTask;
import com.example.maihelper.libraries.getRecords;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SongAdapter songAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        EditText qqEditText = findViewById(R.id.qqEditText);
        Button queryButton = findViewById(R.id.queryButton);
        //初始化适配器
        songAdapter = new SongAdapter(getApplicationContext(), new ArrayList<>());

        recyclerView.setAdapter(songAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        // 添加按钮点击事件的处理逻辑
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提示信息
                Toast.makeText(MainActivity.this, "查询中", Toast.LENGTH_SHORT).show();
                String inputText = qqEditText.getText().toString();
                long qqid = Long.parseLong(inputText);
                //异步
                GetRecordsTask task = new GetRecordsTask(songAdapter, MainActivity.this, MainActivity.this);
                task.execute(qqid);
            }
        });
        //导航栏
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 设置选中项的样式
                item.setChecked(true);
                if (item.getItemId()==R.id.navigation_home){
                    // 切换到主页
                    Intent intent=new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    return true;
                }else if(item.getItemId()==R.id.navigation_profile){
                    // 切换到个人资料页面
                    Intent intent3=new Intent(MainActivity.this,UserActivity.class);
                    startActivity(intent3);
                    return true;
                }
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        // 切换到主页
//                        Intent intent=new Intent(MainActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        return true;
////                    case R.id.navigation_search:
////                        // 切换到搜索页面
////                        switchToSearch();
////                        return true;
//                    case R.id.navigation_profile:
//                        // 切换到个人资料页面
//                        Intent intent3=new Intent(MainActivity.this,UserActivity.class);
//                        startActivity(intent3);
//                        return true;
//                }
                return false;
            }
        });
        //默认选中项样式
        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
    }




}