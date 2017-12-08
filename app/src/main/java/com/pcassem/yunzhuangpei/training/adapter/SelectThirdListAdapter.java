package com.pcassem.yunzhuangpei.training.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

/**
 * Created by zhangqi on 2017/12/7.
 */

public class SelectThirdListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    String[][] secondProcessList;
    public int categoryPoition;
    public int selectedPosition = -1;

    public SelectThirdListAdapter(Context context, String[][] secondProcessList, int position) {
        this.context = context;
        this.secondProcessList = secondProcessList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.categoryPoition = position;
    }

    @Override
    public int getCount() {
        return secondProcessList[categoryPoition].length;
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_third_select_list, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
            viewHolder.underline = (TextView)convertView.findViewById(R.id.underline_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (selectedPosition == position) {
            viewHolder.textView.setTextColor(convertView.getResources().getColor(R.color.color_13386d));
            viewHolder.underline.setVisibility(View.VISIBLE);
        } else {
            viewHolder.textView.setTextColor(convertView.getResources().getColor(R.color.color_999999));
            viewHolder.underline.setVisibility(View.GONE);
        }
        viewHolder.textView.setText(secondProcessList[categoryPoition][position]);
        viewHolder.underline.setText(secondProcessList[categoryPoition][position]);
        return convertView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public TextView textView;
        public TextView underline;
    }


}
