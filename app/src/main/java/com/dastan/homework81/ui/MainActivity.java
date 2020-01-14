package com.dastan.homework81.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dastan.homework81.R;
import com.dastan.homework81.base.BaseActivity;
import com.dastan.homework81.data.RetrofitBuilder;
import com.dastan.homework81.data.entity.Article;
import com.dastan.homework81.data.entity.News;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dastan.homework81.BuildConfig.API_KEY;
import static com.dastan.homework81.BuildConfig.BASE_URL;

public class MainActivity extends BaseActivity implements MainContract.View{

    @BindView(R.id.imgNews)
    ImageView newsImage;
    @BindView(R.id.textTitle)
    TextView titleText;
    @BindView(R.id.textDesc)
    TextView descText;

    RecyclerView recyclerViewNews;

    private MainContract.Presenter mPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MainPresenter();
        mPresenter.bind(this);
        mPresenter.fetchNews();
        initViews();
    }

    private void initViews(){
        recyclerViewNews = findViewById(R.id.rvNews);
    }


    @Override
    public void setNewsData(List<Article> list) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewNews.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter();
        recyclerViewNews.setAdapter(adapter);
        adapter.setNewsData(list);
    }

    @Override
    public void toast(String localizedMessage) {
        Toast.makeText(this, localizedMessage, Toast.LENGTH_SHORT).show();
    }

    public static void start(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    //    private void setResponse(List<News> response) {
//        List<News> newsList = response.body
//        Glide.with(getApplicationContext())
//                .load(BASE_URL
//                        + response.body().getUrlToImage())
//                .into(newsImage);
//        titleText.setText(response);
//        descText.setText(response.body().getDescription());
//    }
}
