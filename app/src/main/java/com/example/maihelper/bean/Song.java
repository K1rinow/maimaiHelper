package com.example.maihelper.bean;
import com.example.maihelper.R;

public class Song {
    private double achievements;    //达成率
    private double ds;//定数
    private int dxScore;//dx分数
    private String fc;//fc
    private String fs;//fs
    private String level;//等级
    private int levelIndex;//等级页
    private String levelLabel;//等级（master等）
    private int ra;//分数(185)
    private String rate;//评级
    private int songId;//歌曲id
    private String title;//标题
    private String type;//类型（dx或sd）
    public int getRankImage(String rt) {
        int resourceId;

        switch (rt) {
            case "sssp":
                resourceId = R.drawable.ui_rank_sssp;
                break;
            case "sss":
                resourceId = R.drawable.ui_rank_sss;
                break;
            case "ssp":
                resourceId = R.drawable.ui_rank_ssp;
                break;
            case "ss":
                resourceId = R.drawable.ui_rank_ss;
                break;
            case "sp":
                resourceId = R.drawable.ui_rank_sp;
                break;
            case "s":
                resourceId = R.drawable.ui_rank_s;
                break;
            case "aaa":
                resourceId = R.drawable.ui_rank_aaa;
                break;
            case "aa":
                resourceId = R.drawable.ui_rank_aa;
                break;
            case "a":
                resourceId = R.drawable.ui_rank_a;
                break;
            case "bbb":
                resourceId = R.drawable.ui_rank_bbb;
                break;
            case "bb":
                resourceId = R.drawable.ui_rank_bb;
                break;
            case "b":
                resourceId = R.drawable.ui_rank_b;
                break;
            case "c":
                resourceId = R.drawable.ui_rank_c;
                break;
            case "d":
                resourceId = R.drawable.ui_rank_d;
                break;
            default:
                resourceId = R.drawable.ui_rank_d; // 默认的图片资源 ID
                break;
        }

        return resourceId;
    }
    public Song(double achievements, double ds, int dxScore, String fc, String fs, String level, int levelIndex, String levelLabel, int ra, String rate, int songId, String title, String type) {
        this.achievements = achievements;
        this.ds = ds;
        this.dxScore = dxScore;
        this.fc = fc;
        this.fs = fs;
        this.level = level;
        this.levelIndex = levelIndex;
        this.levelLabel = levelLabel;
        this.ra = ra;
        this.rate = rate;
        this.songId = songId;
        this.title = title;
        this.type = type;
    }

    public double getAchievements() {
        return achievements;
    }

    public void setAchievements(double achievements) {
        this.achievements = achievements;
    }

    public double getDs() {
        return ds;
    }

    public void setDs(double ds) {
        this.ds = ds;
    }

    public int getDxScore() {
        return dxScore;
    }

    public void setDxScore(int dxScore) {
        this.dxScore = dxScore;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getFs() {
        return fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getLevelIndex() {
        return levelIndex;
    }

    public void setLevelIndex(int levelIndex) {
        this.levelIndex = levelIndex;
    }

    public String getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(String levelLabel) {
        this.levelLabel = levelLabel;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}

