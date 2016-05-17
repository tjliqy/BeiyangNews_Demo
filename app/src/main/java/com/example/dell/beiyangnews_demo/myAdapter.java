package com.example.dell.beiyangnews_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by dell on 2016/4/30.
 */
public class myAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<News> list;
    private LayoutInflater inflater;
    private Context context;
    private final int TYPE_BODY = 0;
    private final int TYPE_FOOTER = 1;

    public myAdapter(Context context , List<News> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == list.size()){
            return TYPE_FOOTER;
        }else {
            return TYPE_BODY;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_FOOTER) {
            FooterViewHolder footerViewHolder = new FooterViewHolder(inflater.inflate(R.layout.footeritem,parent,false));
            return footerViewHolder;

        }
        else {
            myViewHolder holder = new myViewHolder(inflater.inflate(R.layout.item, parent, false));
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type == TYPE_BODY) {
            final myViewHolder myViewHolder = (myViewHolder)holder;
            News news = list.get(position);
//        holder.pic.setImageBitmap(news.getPic());
            Glide.with(context)
                    .load(news.getPic())
                    .into(myViewHolder.pic);
            myViewHolder.title.setText(news.getTitle());
            myViewHolder.visitcount.setText("访问：" + news.getVisitecount());
            myViewHolder.comments.setText("评论：" + news.getComments());
        }
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }
    public class FooterViewHolder extends RecyclerView.ViewHolder{
        public FooterViewHolder(View ItemView){
            super(ItemView);
        }
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView pic;
        private TextView visitcount;
        private TextView comments;

        public myViewHolder(View ItemView){
            super(ItemView);
            title = (TextView) ItemView.findViewById(R.id.title_item);
            pic = (ImageView) ItemView.findViewById(R.id.pic_item);
            visitcount = (TextView) ItemView.findViewById(R.id.visitcount_item);
            comments = (TextView) ItemView.findViewById(R.id.comments_item);
        }
    }



}
