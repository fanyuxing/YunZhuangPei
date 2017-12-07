package com.pcassem.yunzhuangpei.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

import java.util.ArrayList;


public class MyAdvisoryAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> groups;
    private ArrayList<ArrayList<Child>> childs;

    private Context mContext;

    public MyAdvisoryAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<Child>> childs){
        mContext = context;
        this.groups = groups;
        this.childs = childs;
    }
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        /**创建view */
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_my_advisory_parent,null);
        /**通过创建的view与自定义布局绑定，就要以找到布局里的组件了*/
        TextView tvGroupTitle = (TextView)v.findViewById(R.id.tv_group_title);
        ImageView icon = (ImageView) v.findViewById(R.id.icon_my_project);

        tvGroupTitle.setText(groups.get(groupPosition));
        if(isExpanded){
            icon.setBackgroundResource(R.drawable.icon_my_project_up);
        }else{
            icon.setBackgroundResource(R.drawable.icon_my_project_down);
        }
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_my_advisory_child,null);
        TextView tvChildName = (TextView)v.findViewById(R.id.tv_child_project_name);
        TextView tvChildTime = (TextView)v.findViewById(R.id.tv_child_project_time);
        TextView tvChildState = (TextView) v.findViewById(R.id.tv_child_project_state);


        tvChildName.setText(childs.get(groupPosition).get(childPosition).getName());
        tvChildTime.setText(childs.get(groupPosition).get(childPosition).getTime());
        tvChildState.setText(childs.get(groupPosition).get(childPosition).getState());
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}