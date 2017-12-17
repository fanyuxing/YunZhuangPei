package com.pcassem.yunzhuangpei.home.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.pcassem.yunzhuangpei.training.fragments.KnowledgeFragment;

public class SearchKnowledgeAdapter extends FragmentPagerAdapter {

    String repository, keyowrd;

    public SearchKnowledgeAdapter(FragmentManager fm, String repository, String keyword) {
        super(fm);
        this.repository = repository;
        this.keyowrd = keyword;
    }

    @Override
    public Fragment getItem(int position) {
        return KnowledgeFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        KnowledgeFragment fragment = (KnowledgeFragment) super.instantiateItem(container, position);
        fragment.updateSearchKnowledge(repository, keyowrd);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof KnowledgeFragment) {
            ((KnowledgeFragment) object).updateSearchKnowledge(repository, keyowrd);
        }
        return super.getItemPosition(object);
    }


    public void setDate(String repository, String keyowrd) {
        this.repository = repository;
        this.keyowrd = keyowrd;
        notifyDataSetChanged();
    }
}
