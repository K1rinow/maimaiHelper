package com.example.maihelper.libraries;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

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

public class GetRecordsTask extends AsyncTask<Long, Void, ArrayList<Song>> {
    //api
    private static final String targetUrl = "https://www.diving-fish.com/api/maimaidxprober/query/player";
    private ArrayList<Song> songList = new ArrayList<>();
    //适配器，用来在Recyclerview中显示数据
    private SongAdapter songAdapter;
    private Context context;
    private Activity activity;
    //获取b40
    @Override
    protected ArrayList<Song> doInBackground(Long... params) {
        String qqid = String.valueOf(params[0]);

        try {
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");//请求方式:POST
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            //载荷参数设置，
            String customData = "{\"qq\": \"" + qqid + "\", \"b50\": \"NULL\"}";
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
            //解析JSON，
            if(responseCode == HttpURLConnection.HTTP_OK) {
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject chartsObject = jsonResponse.getJSONObject("charts");
                //新歌曲(b15)
                JSONArray dxArray = chartsObject.getJSONArray("dx");
                //老歌曲(b35)
                JSONArray sdArray = chartsObject.getJSONArray("sd");

                for (int i = 0; i < dxArray.length(); i++) {
                    JSONObject dxObject = dxArray.getJSONObject(i);
                    // 处理解析后的数据
                    double achievements = dxObject.getDouble("achievements");
                    double ds = dxObject.getDouble("ds");
                    int dxScore = dxObject.getInt("dxScore");
                    String fc = dxObject.getString("fc");
                    String fs = dxObject.getString("fs");
                    String level = dxObject.getString("level");
                    int levelIndex = dxObject.getInt("level_index");
                    String levelLabel = dxObject.getString("level_label");
                    int ra = dxObject.getInt("ra");
                    String rate = dxObject.getString("rate");
                    int songId = dxObject.getInt("song_id");
                    String title = dxObject.getString("title");
                    String type = dxObject.getString("type");

                    Song s = new Song(achievements, ds, dxScore, fc, fs, level, levelIndex, levelLabel, ra, rate, songId, title, type);
                    songList.add(s);
                }

                for (int i = 0; i < sdArray.length(); i++) {
                    JSONObject sdObject = sdArray.getJSONObject(i);
                    // 处理解析后的数据
                    double achievements = sdObject.getDouble("achievements");
                    double ds = sdObject.getDouble("ds");
                    int dxScore = sdObject.getInt("dxScore");
                    String fc = sdObject.getString("fc");
                    String fs = sdObject.getString("fs");
                    String level = sdObject.getString("level");
                    int levelIndex = sdObject.getInt("level_index");
                    String levelLabel = sdObject.getString("level_label");
                    int ra = sdObject.getInt("ra");
                    String rate = sdObject.getString("rate");
                    int songId = sdObject.getInt("song_id");
                    String title = sdObject.getString("title");
                    String type = sdObject.getString("type");

                    Song s = new Song(achievements, ds, dxScore, fc, fs, level, levelIndex, levelLabel, ra, rate, songId, title, type);
                    songList.add(s);
                }

                connection.disconnect();
            }
            else if (responseCode==HttpURLConnection.HTTP_BAD_REQUEST){
                //玩家错误,弹出提示框
                showInputErrorDialog();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return songList;
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
                        }).create().show();
            }
        });
    }
    public GetRecordsTask(SongAdapter adapter, Context context, Activity activity){
        this.songAdapter=adapter;
        this.context=context;
        this.activity=activity;
    }
    @Override
    protected void onPostExecute(ArrayList<Song> songList) {
        // 更新 UI，将获取到的歌曲数据显示在 RecyclerView 中
      songAdapter.setSonglist(songList);
    }
}

