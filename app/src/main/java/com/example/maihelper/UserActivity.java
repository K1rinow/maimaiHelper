package com.example.maihelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maihelper.libraries.GetUserTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        EditText usernameText;
        ImageView backgroundImage;
        ImageView imageView3;
        ImageView imageView2;
        ImageView imageView4;
        ImageView imageView5;
        ImageView imageView1;
        ImageView nicknameImageView;
        Button queryButton;
        Button saveButton;
        TextView nicknameTextView;

        nicknameTextView = findViewById(R.id.nicknameTextView);
        usernameText = findViewById(R.id.usernameText);
        backgroundImage = findViewById(R.id.backgroundImage);
        imageView3 = findViewById(R.id.imageView3);
        imageView2 = findViewById(R.id.imageView2);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView1 = findViewById(R.id.imageView1);
        nicknameImageView = findViewById(R.id.nicknameImageView);
        queryButton = findViewById(R.id.queryButton);
        saveButton = findViewById(R.id.saveButton);

//        ImageView nameImageView;
//        TextView nicknameView;
//        ImageView friendImageView;
//        ImageView ratingimageView;
//
//        nameImageView = (ImageView) findViewById(R.id.nameImageView);
//        nicknameView = (TextView) findViewById(R.id.nicknameView);
//        friendImageView = (ImageView) findViewById(R.id.friendImageView);
//        ratingimageView = (ImageView) findViewById(R.id.ratingimageView);
////        nameImageView.setVisibility(View.GONE);
////        nicknameView.setVisibility(View.GONE);
////        friendImageView.setVisibility(View.GONE);
////        ratingimageView.setVisibility(View.GONE);
//
//
//
//        EditText usernameText=findViewById(R.id.usernameText);
//        Button queryButton=findViewById(R.id.queryButton);
//        Button saveButton=findViewById(R.id.saveButton);
//        TextView ratingText=findViewById(R.id.ratingText);
//        TextView nicknameText=findViewById(R.id.nicknameText);

        //查询按钮监听器
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框用户名，api查询，返回rating和nickname
                GetUserTask task2=new GetUserTask(backgroundImage,imageView3,imageView2,imageView4,imageView5,imageView1,nicknameImageView,nicknameTextView, UserActivity.this,UserActivity.this);

                task2.execute(usernameText.getText().toString());
            }
        });
        //初始化sp对象，用来保存数据
        SharedPreferences sharedPreferences;
        sharedPreferences=getSharedPreferences("userData", Context.MODE_PRIVATE);

        String username=sharedPreferences.getString("username","");
        String rating = sharedPreferences.getString("rating","114514");
        String nickname=sharedPreferences.getString("nickname","默认用户");

        usernameText.setText(username);
//        ratingText.setText(rating);
//        nicknameText.setText(nickname);

        //保存按钮监听器
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存用户名和rating，nickname到本地。
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username",usernameText.getText().toString());
//                editor.putString("rating",ratingText.getText().toString());
//                editor.putString("nickname",nicknameText.getText().toString());
                editor.apply();
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
                    Intent intent=new Intent(UserActivity.this,MainActivity.class);
                    startActivity(intent);
                    return true;
                }else if(item.getItemId()==R.id.navigation_profile){
                    // 切换到个人资料页面
                    Intent intent3=new Intent(UserActivity.this,UserActivity.class);
                    startActivity(intent3);
                    return true;
                }
                return false;
            }
        });
        //默认选中项样式
        bottomNavigationView.getMenu().findItem(R.id.navigation_profile).setChecked(true);


    }
}