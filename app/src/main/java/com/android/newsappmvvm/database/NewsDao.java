package com.android.newsappmvvm.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.newsappmvvm.model.Article;
import com.android.newsappmvvm.model.MainNews;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addArticle(Article article);

    @Query("SELECT * FROM news_article_table")
    List<Article> getAllNews();

}
