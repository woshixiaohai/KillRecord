package com.qhsoft.killrecord.module.file;

import android.os.Bundle;
import android.widget.ImageView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.base.BaseActivity;
import com.qhsoft.killrecord.view.FreeToolbar;

import butterknife.Bind;

/**
 * Description:
 * Author:lin
 * Date:2017-10-26
 */


public class PicActivity extends BaseActivity {
    @Bind(R.id.mFreeToolbar)
    FreeToolbar mFreeToolbar;
    @Bind(R.id.iv_tool)
    ImageView iv_pic;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_pic;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mFreeToolbar.setTitle("图片查看");
        loadImage(getIntent().getStringExtra("url"), iv_pic);

    }


}
