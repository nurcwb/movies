package com.example.adenirf.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adenirf.movies.utils.MovieLoadToImageView;
import com.example.adenirf.movies.R;
import com.example.adenirf.movies.pojo.Result;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.AdapterViewHolder> {
    private List<Result> results;
    private CLickListener cLickListener;

    public MovieAdapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int idLayoutItemMovie = R.layout.layout_item_movie;
        LayoutInflater inflater = LayoutInflater.from(context);
        Boolean shouldAttachToParentImediately = false;

        View view = inflater.inflate(idLayoutItemMovie, viewGroup, shouldAttachToParentImediately);

        return new AdapterViewHolder(view);
    }

    public void setcLickListener(CLickListener cLickListener) {
        this.cLickListener = cLickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder adapterViewHolder, int i) {
        Result result = results.get(i);

        String pathWithOutBar = (result.getPosterPath().replace("/", ""));

        ImageView imageView = adapterViewHolder.ivPoster;
        MovieLoadToImageView.setImageIntoImageView(pathWithOutBar, imageView, false, true);
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;

        public AdapterViewHolder(@NonNull final View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result result = results.get(getAdapterPosition());
                    cLickListener.OnClickListener(result);
                }
            });
        }
    }
}