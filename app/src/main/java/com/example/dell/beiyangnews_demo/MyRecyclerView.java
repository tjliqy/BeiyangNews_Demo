package com.example.dell.beiyangnews_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import java.util.jar.Attributes;

/**
 * Created by dell on 2016/5/16.
 * 暂时并没有什么卵用
 */
public class MyRecyclerView extends RecyclerView {
    private boolean mIsFooterEnable = false;//是否允许加载更多
    private boolean mIsLoadingMore = false;//是否正在加载更多
    private myAdapter myAdapter;
    private LoadMoreListener listener;//加载更多的监听

    public MyRecyclerView(Context context){
        super(context);
        init();
    }
    public MyRecyclerView(Context context,AttributeSet attrs){
        super(context,attrs);
        init();
    }
    public MyRecyclerView(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        init();
    }
    private void init(){
        super.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(null != listener && mIsFooterEnable && !mIsLoadingMore && dy>0){
                    int lastVisiblePosition = getLastVisiblePosition();
                    if(lastVisiblePosition >= myAdapter.getItemCount()-1){

                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
    public interface LoadMoreListener{
        void onLoadMore();
    }
    private int getLastVisiblePosition(){
        StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
        int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
        int lastPosition = findMax(lastPositions);
        return lastPosition;
    }
    private int findMax(int[] lastPositions) {
        int size = lastPositions.length;
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
