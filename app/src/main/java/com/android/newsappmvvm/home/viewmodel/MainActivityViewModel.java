package com.android.newsappmvvm.home.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.newsappmvvm.BackgroundTask;
import com.android.newsappmvvm.NewsApplication;
import com.android.newsappmvvm.database.NewsDao;
import com.android.newsappmvvm.database.NewsDatabase;
import com.android.newsappmvvm.di.NewsService;
import com.android.newsappmvvm.home.ui.MainActivity;
import com.android.newsappmvvm.model.Article;
import com.android.newsappmvvm.model.MainNews;
import com.android.newsappmvvm.util.ConnectionUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    @Inject
    NewsService newsService;

    @Inject
    NewsDao dao;
    BackgroundTask bg=new BackgroundTask();

    private final String apiKey="612dfad3379e4ed9b82bf6e1746ac0ca";

    private MutableLiveData<MainNews> liveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        ((NewsApplication)application).getNewsComponent().inject(MainActivityViewModel.this);

        liveData=new MutableLiveData();
    }

    public MutableLiveData<MainNews> getLiveData(){
        return liveData;
    }

    public void makeCall(Context context){

//        dao= NewsDatabase.getNewsDatabaseInstance(context).getNewsDao();

        if(!ConnectionUtil.isNetworkConnected(context)){
            bg.getPreviousNews(context,liveData,dao);
            return;
        }

        Call<MainNews> call=newsService.getData("in",apiKey);

        call.enqueue(new Callback<MainNews>() {

            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {

                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                    bg.addAricles(response.body(),dao);
                }
                else liveData.postValue(null);
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }




}
