package com.android.newsappmvvm.home.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.newsappmvvm.R;
import com.android.newsappmvvm.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Article> articleArrayList;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Article> getArticleArrayList() {
        return articleArrayList;
    }

    public void setArticleArrayList(ArrayList<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.news_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.heading.setText(articleArrayList.get(position).getTitle());
        holder.publish.setText(articleArrayList.get(position).getPublishedAt());
        holder.author.setText(articleArrayList.get(position).getAuthor());
        holder.content.setText(articleArrayList.get(position).getDescription());
        Picasso.get().load(articleArrayList.get(position).getUrlToImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        if(articleArrayList==null) return 0;
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView heading,content,author,publish;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading=itemView.findViewById(R.id.heading);
            content=itemView.findViewById(R.id.content);
            author=itemView.findViewById(R.id.author);
            publish=itemView.findViewById(R.id.publish);
            image=itemView.findViewById(R.id.image);

        }
    }
}
