package com.qhsoft.killrecord.view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qhsoft.killrecord.R;

/**
 * Description:
 * Author:hong
 * Date:2017-09-08
 */

public class SpaceDividerDecoration extends RecyclerView.ItemDecoration {
    private boolean isLoadMore;

    public SpaceDividerDecoration(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int space = parent.getResources().getDimensionPixelSize(R.dimen.divider_space_height);
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.set(0, space, 0, space);
        } else {
            if (isLoadMore) {
                if (position>= parent.getAdapter().getItemCount() - 2){
                    outRect.set(0, 0, 0, 0);
                }else{
                    outRect.set(0, 0, 0, space);
                }
            }else{
                outRect.set(0, 0, 0, space);
            }

        }
    }
}
