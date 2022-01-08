package com.android.newsappmvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.content.AsyncTaskLoader;

import com.android.newsappmvvm.database.NewsDao;
import com.android.newsappmvvm.model.Article;
import com.android.newsappmvvm.model.MainNews;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.List;

public class BackgroundTask {


    public void getPreviousNews(Context context, MutableLiveData<MainNews> liveData, NewsDao dao) {
        class GetNews extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                List<Article> articles=dao.getAllNews();
                MainNews mainNews=new MainNews();
                mainNews.setArticles((ArrayList<Article>) articles);
                mainNews.setStatus("OK");
                mainNews.setTotalResults(99);
                liveData.postValue(mainNews);
                return null;
            }
        }
        GetNews getNews=new GetNews();
        getNews.execute();
    }


    public void addAricles(MainNews body,NewsDao dao) {
        ArrayList<Article> articles=body.getArticles();
        if(articles!=null && !articles.isEmpty()){
            for(Article x:articles){
                addDataInBg(x,dao);
            }
        }

    }

    public void addDataInBg(Article x,NewsDao dao) {
        class AsynDataSaver extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                dao.addArticle(x);
                return null;
            }
        }
        AsynDataSaver asynDataSaver=new AsynDataSaver();
        asynDataSaver.execute();
    }



}
