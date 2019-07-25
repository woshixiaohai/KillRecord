package com.qhsoft.killrecord.view.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.util.DisplayUtils;

/**
 * Description:
 * Author:hong
 * Date:2017-09-08
 */

public class DividerOverDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;

    public DividerOverDecoration() {
        mPaint = new Paint();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        mPaint.setColor(ContextCompat.getColor(parent.getContext(), R.color.common_divider));
        int childCount = parent.getChildCount();
        Context context = parent.getContext();

        int dividerHeight = DisplayUtils.dip2px(context, context.getResources().getDimensionPixelSize(R.dimen.divider_thin_height));
        int space = context.getResources().getDimensionPixelSize(R.dimen.divider_spacing_common);

        for (int index = 0; index < childCount; index++) {
            if (index == childCount - 1) {
                continue;
            }
            View child = parent.getChildAt(index);
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            int childAdapterPosition = parent.getChildAdapterPosition(child);
            if (childAdapterPosition != parent.getAdapter().getItemCount() - 1) {
                c.drawRect(child.getLeft() + space, child.getBottom(), child.getRight() - space, child.getBottom() + dividerHeight
                        , mPaint);
            }
        }
    }
}
