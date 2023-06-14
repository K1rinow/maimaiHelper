package com.example.maihelper.libraries;

import android.os.AsyncTask;

import com.example.maihelper.R;
import com.example.maihelper.bean.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maihelper.MainActivity;
import com.example.maihelper.bean.Song;

import com.example.maihelper.bean.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.example.maihelper.adaoter.SongAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetUserTask extends AsyncTask<String,Void, ArrayList<User>> {
    private static final String targetUrl = "https://www.diving-fish.com/api/maimaidxprober/query/player";
    private ArrayList<User> userList=new ArrayList<>();
    private Context context;
    private Activity activity;
    private TextView ratingText;
    private TextView nicknameText;
    ImageView backgroundImage;
    ImageView imageView3;
    ImageView imageView2;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView1;
    ImageView nicknameImageView;
    ImageView nameImageView;
    TextView nicknameView;
    ImageView friendImageView;
    ImageView ratingimageView;
    TextView nicknameTextView;
    @Override
    protected ArrayList<User> doInBackground(String... strings) {
        String username= strings[0];
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String customData = "{\"username\": \"" + username + "\"}";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(customData.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            BufferedReader reader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                JSONObject jsonResponse = new JSONObject(response.toString());
                String nickname=jsonResponse.getString("nickname");
                String rating=jsonResponse.getString("rating");
                User u=new User(username,nickname,rating);
                userList.add(u);
                connection.disconnect();
            }
            else if (responseCode==HttpURLConnection.HTTP_BAD_REQUEST){
                //玩家错误,弹出提示框
                showInputErrorDialog();
            }
            // 解析响应数据并添加到userList

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }
    private void showInputErrorDialog() {
        //对话框的创建和显示要在ui线程中完成
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("错误")
                        .setMessage("没有找到此玩家，请确认输入正确")
                        .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });

    }
    public GetUserTask(ImageView backgroundImage,
    ImageView imageView3,
    ImageView imageView2,
    ImageView imageView4,
    ImageView imageView5,
    ImageView imageView1,
    ImageView nicknameImageView, TextView nicknameTextView,Context context, Activity activity){
        this.context=context;
        this.activity=activity;
        this.backgroundImage=backgroundImage;
        this.imageView3=imageView3;
        this.imageView2=imageView2;
        this.imageView4=imageView4;
        this.imageView5=imageView5;
        this.imageView1=imageView1;
        this.nicknameImageView=nicknameImageView;
        this.nicknameTextView=nicknameTextView;

    }

    protected void onPostExecute(ArrayList<User> userList) {
        //更新 UI,判空
        if( userList != null&& !userList.isEmpty()) {
//            nicknameText.setText(userList.get(0).getNickname());
//            ratingText.setText(userList.get(0).getRating());
            nicknameTextView.setText(userList.get(0).getNickname());
            String rating=userList.get(0).getRating();
            // 检查长度，补零
            if (rating.length() < 5) {
                int zerosToAdd = 5 - rating.length();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < zerosToAdd; i++) {
                    builder.append("0");
                }
                builder.append(rating);
                rating = builder.toString();
            }
            ArrayList<ImageView> imageViewList = new ArrayList<>();
            imageViewList.add(imageView1);
            imageViewList.add(imageView2);
            imageViewList.add(imageView3);
            imageViewList.add(imageView4);
            imageViewList.add(imageView5);
            //提取每个字符
            for (int i = 0; i < rating.length(); i++) {

                String s = rating.substring(i, i + 1);
                int num=Integer.parseInt(s);

                switch (num) {
                    case 0:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_0);
                        break;
                    case 1:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_1);
                        break;
                    case 2:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_2);
                        break;
                    case 3:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_3);
                        break;
                    case 4:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_4);
                        break;
                    case 5:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_5);
                        break;
                    case 6:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_6);
                        break;
                    case 7:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_7);
                        break;
                    case 8:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_8);
                        break;
                    case 9:
                        imageViewList.get(i).setImageResource(R.drawable.ui_num_9);
                        break;
                }
            }
        }else {

        }
    }
}
