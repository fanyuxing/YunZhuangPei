package com.pcassem.yunzhuangpei.home.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

import java.util.List;

public class CommentListAdapter extends BaseAdapter {

    Context context;
    List<Comment> data;

    public CommentListAdapter(Context c, List<Comment> data) {
        this.context = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

            ViewHolder viewHolder;
            // 重用convertView
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_news_comment_list, null);
                viewHolder.comment_name = (TextView) convertView.findViewById(R.id.comment_name);
                viewHolder.comment_content = (TextView) convertView.findViewById(R.id.comment_content);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // 适配数据
            viewHolder.comment_name.setText(data.get(i).getName());
            viewHolder.comment_content.setText(data.get(i).getContent());

        return convertView;

    }

    public void addComment(Comment comment) {
        if (data.size() == 0){
            data.add(0,comment);
        }else {
            data.add(1,comment);
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder {
        TextView comment_name;
        TextView comment_content;
    }
}

