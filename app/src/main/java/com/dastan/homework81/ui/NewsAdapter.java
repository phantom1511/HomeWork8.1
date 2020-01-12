package com.dastan.homework81.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dastan.homework81.R;
import com.dastan.homework81.data.entity.Article;

import java.util.ArrayList;
import java.util.List;

import static com.dastan.homework81.BuildConfig.BASE_URL;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> implements OnItemClickListener {

    private List<Article> newsData;
    private Context context;

//    public NewsAdapter(List<Article> newsData) {
//        this.newsData = newsData;
//    }

    public void setNewsData(List<Article> newsData){
        this.newsData = newsData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        newsViewHolder.setOnItemClickListener(this);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Glide.with(holder.newsImg.getContext())
                .load(BASE_URL
                        + newsData.get(position).getUrlToImage())
                .into(holder.newsImg);
        holder.newsTitle.setText(newsData.get(position).getTitle());
        holder.newsDesc.setText(newsData.get(position).getDescription());
    }


    @Override
    public int getItemCount() {
        return newsData.size();
    }

    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra("news", newsData.get(position).getUrl());
        context.startActivity(intent);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private ImageView newsImg;
        private TextView newsTitle;
        private TextView newsDesc;
        private LinearLayout linearLayout;
        private OnItemClickListener onItemClickListener;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            newsImg = itemView.findViewById(R.id.imgNews);
            newsTitle = itemView.findViewById(R.id.textTitle);
            newsDesc = itemView.findViewById(R.id.textDesc);
            linearLayout = itemView.findViewById(R.id.idListNews);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener = onItemClickListener;
        }
    }

}

