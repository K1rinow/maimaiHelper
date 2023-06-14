package com.example.maihelper.libraries;
//没用

import com.example.maihelper.bean.Song;
//作废，网络请求不能在主线程
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class getRecords {

    public ArrayList<Song> getB50(int qqid) {
        String targetUrl = "https://www.diving-fish.com/api/maimaidxprober/query/player";
        ArrayList<Song> songlIST = new ArrayList<>();
        try {
            // 创建 URL 对象
            URL url = new URL(targetUrl);

            // 创建 HttpURLConnection 对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // 自定义信息
            String customData = "{\"qq\": \"1165516048\", \"b50\": \"NULL\"}"; // 自定义信息的 JSON 字符串

            // 发送请求
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(customData.getBytes());
            outputStream.flush();
            outputStream.close();

            // 获取响应
            int responseCode = connection.getResponseCode();
            BufferedReader reader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            // 读取响应内容
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 处理响应数据
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + response.toString());

            JSONObject jsonResponse = new JSONObject(response.toString());

            // 根据 JSON 数据的结构，解析一级字段
            JSONObject chartsObject = jsonResponse.getJSONObject("charts");

            // 获取二级字段 dx 对应的 JSON 数组
            JSONArray dxArray = chartsObject.getJSONArray("dx");
            JSONArray sdArray = chartsObject.getJSONArray("sd");


            // 遍历 dx 数组
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
                songlIST.add(s);
            }
//            songlIST.forEach((e) -> {
//                System.out.println("Record:");
//                System.out.println(e.getAchievements());
//                System.out.println(e.getTitle());
//            });
            // 遍历 sd 数组
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
                songlIST.add(s);
            }
            // 关闭连接
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return songlIST;

    }
}
