package com.android.newsappmvvm.home.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.newsappmvvm.R;
import com.android.newsappmvvm.home.adapter.RecyclerViewAdapter;
import com.android.newsappmvvm.home.viewmodel.MainActivityViewModel;
import com.android.newsappmvvm.model.Article;
import com.android.newsappmvvm.model.MainNews;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    RecyclerViewAdapter recyclerViewAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        getData();

    }

    private void getData() {
        mainActivityViewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getLiveData().observe(this, new Observer<MainNews>() {
            @Override
            public void onChanged(MainNews mainNews) {
                recyclerViewAdapter.setContext(MainActivity.this);
                recyclerViewAdapter.setArticleArrayList(mainNews.getArticles());
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
        mainActivityViewModel.makeCall(this);
    }

    private void initRecyclerView() {

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter=new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}