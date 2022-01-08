package com.android.newsappmvvm.di;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.android.newsappmvvm.NewsApplication;
import com.android.newsappmvvm.database.NewsDao;
import com.android.newsappmvvm.database.NewsDatabase;
//import com.android.newsappmvvm.database.NewsDatabase_Impl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NewsModule {

    private final String baseUrl="https://newsapi.org/v2/";

    @Singleton
    @Provides
    public NewsService newsServiceApi(Retrofit retrofit){
        return retrofit.create(NewsService.class);
    }

    @Singleton
    @Provides
    public Retrofit getRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Singleton
    @Provides
    public NewsDao getNewsDaoInstance(){
        NewsDao dao=NewsDatabase.getNewsDatabaseInstance(NewsApplication.context).getNewsDao();
        return dao;
    }
}
