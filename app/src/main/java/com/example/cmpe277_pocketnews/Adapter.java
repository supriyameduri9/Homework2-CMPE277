package com.example.cmpe277_pocketnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cmpe277_pocketnews.Models.ArticleList;

import java.util.List;

import static com.example.cmpe277_pocketnews.Constants.formatDateTime;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ArticleList> articles;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public Adapter(List<ArticleList> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(context).inflate(R.layout.item, parent,false);
         return new ViewHolder(view, onItemClickListener);
    }
    @Override
        public void onBindViewHolder(@NonNull ViewHolder holders, int position) {
                final ViewHolder holder = holders;
                ArticleList model = articles.get(position);

                String imageUrl = model.getUrlToImage();
                Glide.with(context).load(imageUrl).into(holder.imageView);

                holder.title.setText(model.getTitle());
                holder.desc.setText(model.getDescription());
                holder.source.setText(model.getSource().getName());
                holder.time.setText(formatDateTime(model.getPublishedAt()));
                holder.author.setText(model.getAuthor());
            }
    @Override
    public int getItemCount() {
        return articles.size();
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        //this.OnItemClickListener =  onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title,desc,author,source,time;
        ImageView imageView;

        OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.image);

            this.onItemClickListener = onItemClickListener;
          //  imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
