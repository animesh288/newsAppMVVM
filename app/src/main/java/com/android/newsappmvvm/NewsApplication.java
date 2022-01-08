package com.android.newsappmvvm;

import android.app.Application;
import android.content.Context;


//import com.android.newsappmvvm.di.DaggerNewsComponent;
import com.android.newsappmvvm.di.DaggerNewsComponent;
import com.android.newsappmvvm.di.NewsComponent;
import com.android.newsappmvvm.di.NewsModule;

public class NewsApplication extends Application {

    public static Context context;
    private NewsComponent newsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;


        newsComponent= DaggerNewsComponent.builder().newsModule(new NewsModule()).build();

    }

     public NewsComponent getNewsComponent(){
        return newsComponent;
     }
}
