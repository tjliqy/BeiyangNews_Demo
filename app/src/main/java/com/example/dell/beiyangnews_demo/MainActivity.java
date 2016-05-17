package com.example.dell.beiyangnews_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private myAdapter myAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page;
    private List<News> list;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private boolean isLoadingMore = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //上拉加载更多
        assert recyclerView != null;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!isLoadingMore) {
                    int into[] = new int[staggeredGridLayoutManager.getSpanCount()];
                    int[] lastVisibleItems = staggeredGridLayoutManager.findLastVisibleItemPositions(into);
                    int lastVisibleItem = findMax(lastVisibleItems);
                    if (dy > 0 && lastVisibleItem >= myAdapter.getItemCount() - 1) {
                        page++;
                        addnewsAsync addnewsAsync = new addnewsAsync(page);
                        addnewsAsync.execute();
                    }
                }
            }

            private int findMax(int[] lastPositions) {
                int size = lastPositions.length;
                Log.d("123",size+"");
                int max = lastPositions[0];
                for (int value : lastPositions) {
                    if (value > max) {
                        max = value;
                    }
                }
                Log.d("123",max+"");
                return max;
            }
        });

        //下拉刷新
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefreshlayout);
        assert swipeRefreshLayout != null;
        swipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.removeAll(list);
                page = 1;
                addnewsAsync addnewsAsync = new addnewsAsync(page);
                addnewsAsync.execute();
            }
        });


        page = 1;
        newsAsync newsAsync = new newsAsync(recyclerView,page);
        newsAsync.execute();
    }

    class newsAsync extends AsyncTask<URL,Integer,List<News>>{

        private RecyclerView recyclerView;
        private String url;

        public newsAsync(RecyclerView recyclerView,int page){
            this.recyclerView = recyclerView;
            this.url = "http://open.twtstudio.com/api/v1/news/1/page/"+page;
        }
        @Override
        protected List<News> doInBackground(URL... params) {
            return parseJson(HttpURL.requestByGet(url));
        }

        @Override
        protected void onPostExecute(List<News> l) {
            myAdapter = new myAdapter(MainActivity.this, l);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            //点击事件
            myAdapter.setOnItemClickLitener(new myAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent();
                    intent.putExtra("index", list.get(position).getIndex());
                    intent.setClass(MainActivity.this, NewsContentActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
            recyclerView.setAdapter(myAdapter);
        }
    }
    class addnewsAsync extends AsyncTask<URL,Integer,List<News>>{

        private String url;

        public addnewsAsync(int page){
            this.url = "http://open.twtstudio.com/api/v1/news/1/page/"+page;
        }

        @Override
        protected void onPreExecute() {
            isLoadingMore = true;
        }

        @Override
        protected List<News> doInBackground(URL... params) {
            return parseJson(HttpURL.requestByGet(url));
        }

        @Override
        protected void onPostExecute(List<News> l) {
            myAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
            isLoadingMore = false;
        }
    }
    private List<News> parseJson(String strResult) {
        try {
            News news;
            //Bitmap bitmap;
            JSONArray jsonObjs = new JSONObject(strResult).getJSONArray("data");
            for (int i = 0; i < jsonObjs.length(); i++) {
                news = new News();
                JSONObject jsonObj = jsonObjs.getJSONObject(i);
                news.setIndex(jsonObj.getInt("index"));
                news.setTitle(jsonObj.getString("subject"));
                //bitmap = HttpURL.GetBitmap(jsonObj.getString("pic"));
                news.setPic(jsonObj.getString("pic"));
                news.setVisitecount(jsonObj.getInt("visitcount"));
                news.setComments(jsonObj.getInt("comments"));
                news.setSummary(jsonObj.getString("summary"));
                list.add(news);
            }
        } catch (JSONException e) {
            System.out.println("Json parse error");
            e.printStackTrace();
        }
        return list;
    }
}


