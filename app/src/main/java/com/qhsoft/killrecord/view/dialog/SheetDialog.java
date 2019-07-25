package com.qhsoft.killrecord.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qhsoft.killrecord.R;

import java.util.List;

/**
 * Description:
 * Author:lin
 * Date:2017-09-11
 */

public class SheetDialog extends DialogFragment {
    private RecyclerView mRecyclerView;

    private Adapter mAdapter;

    private OnSelectedListener onSelectedListener;
    public interface OnSelectedListener{
         void onSelected(int itemPosition, String actionName);
    }
    public void setOnSelectedListener(OnSelectedListener onSelectedListener){
      this.onSelectedListener=onSelectedListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.ActionSheetDialogStyle);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_sheet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new Adapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.updateData(getArguments().getStringArrayList("actionTextList"));
        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                onSelectedListener.onSelected(position,getArguments().getStringArrayList("actionTextList").get(position));

                dismiss();
            }
        });

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

        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.gravity = Gravity.BOTTOM;
        //重新配置属性
        window.setAttributes(wlp);
    }

    private static class Adapter extends RecyclerView.Adapter<Adapter.ItemHolder> {
        private List<String> actionSheetList;
        private LayoutInflater mInflater;
        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public Adapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemHolder(mInflater.inflate(R.layout.list_item_dialog_sheet, parent, false));
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, final int position) {
            holder.tvActionSheetName.setText(actionSheetList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }

        public void updateData(List<String> actionSheetList) {
            this.actionSheetList = actionSheetList;
            notifyDataSetChanged();
        }

        private String getItem(int position) {
            return actionSheetList.get(position);
        }

        @Override
        public int getItemCount() {
            return actionSheetList == null ? 0 : actionSheetList.size();
        }


        static class ItemHolder extends RecyclerView.ViewHolder {
            private TextView tvActionSheetName;

            public ItemHolder(View itemView) {
                super(itemView);
                tvActionSheetName = (TextView) itemView.findViewById(R.id.tv_action_sheet_name);
            }
        }

        interface OnItemClickListener {
            void onItemClick(int position);
        }


    }
}
