package com.dastan.homework81.ui;

import com.dastan.homework81.data.RetrofitBuilder;
import com.dastan.homework81.data.entity.Article;
import com.dastan.homework81.data.entity.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dastan.homework81.BuildConfig.API_KEY;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    @Override
    public void bind(MainContract.View view) {
        mView = view;
    }

    @Override
    public void fetchNews() {
        RetrofitBuilder.getNewsService().currentNews("ru", API_KEY).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> newsList = response.body().getArticles();
                    mView.setNewsData(newsList);
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                mView.toast(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void unbind() {
        mView = null;
    }
}
