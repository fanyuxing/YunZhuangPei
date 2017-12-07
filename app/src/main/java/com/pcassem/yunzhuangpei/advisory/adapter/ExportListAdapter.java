package com.pcassem.yunzhuangpei.advisory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.view.FlowLayout;

import java.util.ArrayList;

/**
 * Created by zhangqi on 2017/11/20.
 */

public class ExportListAdapter extends RecyclerView.Adapter<ExportListAdapter.ViewHolder> implements View.OnClickListener{


    private ArrayList<String> mData;
    private String[] mVals = new String[] { "防水", "构件安装", "开裂", "管廊施工"};
    private LayoutInflater mInflater;

    public void setmOnItemClickListener(ExportListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private ExportListAdapter.OnItemClickListener mOnItemClickListener = null;

    public static interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public ExportListAdapter(ArrayList<String> data){
        this.mData = data;
    }

    @Override
    public ExportListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mInflater = LayoutInflater.from(parent.getContext());

        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advisory_export_recycler_view , parent, false);
        // 实例化viewholder
        ExportListAdapter.ViewHolder viewHolder = new ExportListAdapter.ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExportListAdapter.ViewHolder holder, int position) {
        // 绑定数据
        holder.mTextView.setText(mData.get(position));

        holder.itemView.setTag(position);

        holder.mFlowLayout.removeAllViews();
        for (int i = 0; i < mVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(R.layout.item_advisory_export_tags, holder.mFlowLayout, false);
            tv.setText(mVals[i]);
            holder.mFlowLayout.addView(tv);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        FlowLayout mFlowLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_tv_export);
            mFlowLayout = (FlowLayout) itemView.findViewById(R.id.flowlayout_export_tags);
        }
    }
}
