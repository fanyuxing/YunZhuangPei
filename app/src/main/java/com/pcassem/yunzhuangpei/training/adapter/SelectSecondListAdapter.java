package com.pcassem.yunzhuangpei.training.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

/**
 * Created by zhangqi on 2017/12/4.
 */

public class SelectSecondListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    String[] standard;
    private int selectedPosition = -1;

    public void setStandard(String[] standard) {
        this.standard = standard;
    }

    public SelectSecondListAdapter(Context context, String[] standard) {
        this.context = context;
        this.standard = standard;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return standard.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_second_select_list, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.text_view);
            holder.underline = (TextView) convertView.findViewById(R.id.underline_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (selectedPosition == position) {
            holder.textView.setTextColor(convertView.getResources().getColor(R.color.color_13386d));
            holder.underline.setVisibility(View.VISIBLE);
        } else {
            holder.textView.setTextColor(convertView.getResources().getColor(R.color.color_999999));
            holder.underline.setVisibility(View.GONE);
        }
        holder.textView.setText(standard[position]);
        holder.underline.setText(standard[position]);
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public TextView underline;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

}
