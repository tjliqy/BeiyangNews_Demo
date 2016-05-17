package com.example.dell.beiyangnews_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.StringReader;

/**
 * Created by dell on 2016/5/1.
 */
public class NewsContentActivity extends Activity {
    TextView subject;
    WebView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newscontent);

        subject = (TextView)findViewById(R.id.subject_content);
        content = (WebView)findViewById(R.id.content_content);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        int index = bundle.getInt("index");
        myAsync myAsync = new myAsync(index);
        myAsync.execute();

    }

    class myAsync extends AsyncTask<String,Integer,NewsContent>{

        int index;

        public myAsync(int index){
            this.index = index;
        }
        @Override
        protected void onPostExecute(NewsContent newsContent) {
            subject.setText(newsContent.getSubject());
            content.loadData(newsContent.getContent(),"text/html;charset=utf-8", null);
        }

        @Override
        protected NewsContent doInBackground(String... params) {
            String s = "http://open.twtstudio.com/api/v1/news/" + index;
            return parseJson(HttpURL.requestByGet(s));
        }
    }
    private NewsContent parseJson(String strResult) {
        NewsContent newsContent = new NewsContent();
        try {
            JSONObject jsonObj = new JSONObject(strResult).getJSONObject("data");
            newsContent.setSubject(jsonObj.getString("subject"));
            newsContent.setContent(jsonObj.getString("content"));
            return newsContent;
        } catch (JSONException e) {
            System.out.println("Json parse error");
            e.printStackTrace();
            return null;
        }
    }
}
