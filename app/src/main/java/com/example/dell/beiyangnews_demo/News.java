package com.example.dell.beiyangnews_demo;

import android.graphics.Bitmap;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dell on 2016/4/18.
 */
public class News {
    private int index;
    private String title;
    private int visitecount;
    private String pic;
    private int comments;
    private String summary;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVisitecount() {
        return visitecount;
    }

    public void setVisitecount(int visitecount) {
        this.visitecount = visitecount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
