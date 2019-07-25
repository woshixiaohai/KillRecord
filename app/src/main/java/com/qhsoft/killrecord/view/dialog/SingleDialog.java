package com.qhsoft.killrecord.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qhsoft.killrecord.R;
import com.qhsoft.killrecord.util.DisplayUtils;
import com.qhsoft.killrecord.view.holder.SelectorHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Description:
 * Author:lin
 * Date:2017-09-14
 */

public class SingleDialog extends DialogFragment {

    private RecyclerView mRecyclerView;
    private TextView tvTitle;
    private Adapter mAdapter;
    @Bind(R.id.tv_query)
    TextView tv_query;
    private  List<String> list;

    private OnSelectedItemListener onSelectedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    public void setQueryVisible(){

        if(list!=null){
            if(list.size()>12){
                mRecyclerView.setMinimumHeight(900);
                tv_query.setVisibility(View.VISIBLE);
            }
        }

    }
    @OnTextChanged(R.id.tv_query)
    public void onTextChangedQuery(CharSequence s, int start, int before, int count){


       if(!s.toString().equals("")){

           List<String> list_new=new ArrayList<>();

           for(int i=0;i<list.size();i++){
               if(list.get(i).indexOf(s.toString())>=0){
                   list_new.add(list.get(i));
               }


           }

           mAdapter.setList(list_new);


       }else {
           mAdapter.setList(list);
       }


    }

    public void setOnSelectedListener(OnSelectedItemListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.AlertDialogStyle);
        if(list==null){
            list = getArguments().getStringArrayList("selectList");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_select_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        tvTitle = (TextView) view.findViewById(R.id.tv_name);
        String title = getArguments().getString("title");
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new ItemDecoration());
        mAdapter = new Adapter(getContext());

        List<String> list = getArguments().getStringArrayList("selectList");
        if (list != null) {
            mAdapter.setList(list);
            int selectedPosition = -1;
            String selectedName = getArguments().getString("selectedName");
            if (!TextUtils.isEmpty(selectedName)) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectedName.equals(list.get(i))) {
                        selectedPosition = i;
                        break;
                    }
                }
            } else {
                selectedPosition = getArguments().getInt("selectedPosition", -1);
                if (selectedPosition > mAdapter.getItemCount() - 1) {
                    selectedPosition = -1;

                }
            }
            mAdapter.setSelectedPosition(selectedPosition);
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                    if (onSelectedListener != null) {
                        onSelectedListener.onSelectedItem(position);
                    }
                    dismiss();
                }
            });
        }

        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.width = (int) (DisplayUtils.getPhoneScreenParam(getActivity())[0] * 0.8);
        ;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.gravity = Gravity.CENTER;
        //重新配置属性
        window.setAttributes(wlp);
    }

    private static class ItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int thin = parent.getResources().getDimensionPixelOffset(R.dimen.divider_thin_height);
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.set(0, 0, thin, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }

    private static class Adapter extends RecyclerView.Adapter<SelectorHolder> {
        private LayoutInflater mInflater;
        private List<String> stringList;
        private int mSelectedPosition = -1;
        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public String getItem(int position) {
            return stringList.get(position);
        }

        public Adapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public SelectorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SelectorHolder(mInflater, parent);
        }

        @Override
        public void onBindViewHolder(SelectorHolder holder, final int position) {
            holder.setNameText(stringList.get(position));
            holder.setSelected(mSelectedPosition == position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }

        public void setSelectedPosition(int selectedPosition) {
            if (mSelectedPosition == -1) {
                mSelectedPosition = selectedPosition;
                notifyItemChanged(mSelectedPosition);
            } else {
                int cachePosition = mSelectedPosition;
                mSelectedPosition = selectedPosition;
                notifyItemChanged(cachePosition);
                notifyItemChanged(mSelectedPosition);
            }
        }

        public void setList(List<String> stringList) {
            this.stringList = stringList;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return stringList == null ? 0 : stringList.size();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private interface OnItemClickListener {
        void onItemClick(int position);
    }
}
