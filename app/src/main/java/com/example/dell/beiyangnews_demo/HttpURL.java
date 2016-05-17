package com.example.dell.beiyangnews_demo;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;

/**
 * Created by dell on 2016/4/28.
 */
public class HttpURL {
    public static String requestByGet(String s) {
        try {
            URL url = new URL(s);
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            String result = response.toString();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static Bitmap GetBitmap(String s){
//
//        try {
//            URL url = new URL(s);
//            InputStream in = url.openStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(in);
//            return myBitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
