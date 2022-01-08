package com.android.newsappmvvm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.newsappmvvm.model.Article;
import com.android.newsappmvvm.model.MainNews;


@Database(

        entities = {Article.class},
        version = 1,
        exportSchema = false
)
public abstract class NewsDatabase extends RoomDatabase {

    static NewsDatabase newsDatabaseInstance;
    public abstract NewsDao getNewsDao();


    public static NewsDatabase getNewsDatabaseInstance(Context context) {
        if(newsDatabaseInstance==null){
            newsDatabaseInstance=Room.databaseBuilder(context.getApplicationContext(),NewsDatabase.class,"NEWS_DATABASE")
                    .allowMainThreadQueries().build();
        }
        return newsDatabaseInstance;
    }
}
