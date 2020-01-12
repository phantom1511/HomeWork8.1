package com.dastan.homework81.ui;

import androidx.lifecycle.Lifecycle;

import com.dastan.homework81.Lifecycles;
import com.dastan.homework81.data.entity.Article;

import java.util.List;

interface MainContract {
    interface View{
        void setNewsData(List<Article> list);
        void toast(String localizedMessage);
    }

    interface Presenter extends Lifecycles<View> {
        void fetchNews();
    }
}
