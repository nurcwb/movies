package com.example.adenirf.movies.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemOffSetDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffSet;

    public ItemOffSetDecoration(int mItemOffSet) {
        this.mItemOffSet = mItemOffSet;
    }

    public ItemOffSetDecoration(@NonNull Context context, @DimenRes int mItemOffSet) {
        this(context.getResources().getDimensionPixelSize(mItemOffSet));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemOffSet, mItemOffSet, mItemOffSet, mItemOffSet);
    }
}

