package com.example.dell.beiyangnews_demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dell on 2016/5/1.
 */
public class NewsContent {
    public int index;
    public String subject;//标题
    public String content;//内容
    public String newscome;//新闻来源
    public String gonggao;//供稿
    public String shengao;//审稿
    public String sheying;//摄影
    public String comments;//评论

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewscome() {
        return newscome;
    }

    public void setNewscome(String newscome) {
        this.newscome = newscome;
    }

    public String getGonggao() {
        return gonggao;
    }

    public void setGonggao(String gonggao) {
        this.gonggao = gonggao;
    }

    public String getShengao() {
        return shengao;
    }

    public void setShengao(String shengao) {
        this.shengao = shengao;
    }

    public String getSheying() {
        return sheying;
    }

    public void setSheying(String sheying) {
        this.sheying = sheying;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
