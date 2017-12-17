package com.pcassem.yunzhuangpei.home.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.home.adapter.SearchHistoryAdapter;
import com.pcassem.yunzhuangpei.home.adapter.SearchKnowledgeAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeSearchActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    public static final String TAG = "HomeSearchActivity";
    public static final String EXTRA_KEY_KEYWORD = "extra_key_keyword";
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";

    private EditText mSearchEdt;
    private TextView mCancelTv;
    private TextView mEmptiedTv;
    private ListView mSearchHistoryRecyclerView;
    private LinearLayout mSearchHistory;
    private LinearLayout mSearchAnswer;


    private SearchHistoryAdapter mSearchHistoryAdapter;
    private List<Map<String, Object>> data = null;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    //tab选择栏实现
    private TextView tv_item_one;
    private TextView tv_item_two;
    private TextView tv_item_three;
    private TextView tv_item_four;

    private TextView tv_one_underline;
    private TextView tv_two_underline;
    private TextView tv_three_underline;
    private TextView tv_four_underline;

    private ViewPager myViewPager;
    private SearchKnowledgeAdapter adapter;
    private String repository = "all";
    private String keyword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = getSharedPreferences("HomeSearchHistory", Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        data = new ArrayList<Map<String, Object>>();
        setContentView(R.layout.activity_home_search);

        initView();
        initTouchEvent();
        mSearchEdt.clearFocus();

        initSearchHistory();
    }

    private void initView() {

        mSearchEdt = (EditText) findViewById(R.id.home_search_edt);
        mCancelTv = (TextView) findViewById(R.id.home_search_cancel);
        mEmptiedTv = (TextView) findViewById(R.id.home_search_emptied);
        mSearchHistory = (LinearLayout) findViewById(R.id.home_search_history);
        mSearchAnswer = (LinearLayout) findViewById(R.id.search_answer);
        mSearchHistoryRecyclerView = (ListView) findViewById(R.id.home_searchhistory_recyclerview);
        tv_item_one = (TextView) findViewById(R.id.tv_item_one);
        tv_item_two = (TextView) findViewById(R.id.tv_item_two);
        tv_item_three = (TextView) findViewById(R.id.tv_item_three);
        tv_item_four = (TextView) findViewById(R.id.tv_item_four);
        tv_one_underline = (TextView) findViewById(R.id.tv_one_underline);
        tv_two_underline = (TextView) findViewById(R.id.tv_two_underline);
        tv_three_underline = (TextView) findViewById(R.id.tv_three_underline);
        tv_four_underline = (TextView) findViewById(R.id.tv_four_underline);
        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
    }

    private void initTouchEvent() {

        mCancelTv.setOnClickListener(this);
        mEmptiedTv.setOnClickListener(this);

        myViewPager.setOnPageChangeListener(new MyPagerChangeListener());
        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
        tv_item_three.setOnClickListener(this);
        tv_item_four.setOnClickListener(this);
        mSearchEdt.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_item_one:
                myViewPager.setCurrentItem(0);
                break;
            case R.id.tv_item_two:
                myViewPager.setCurrentItem(1);
                break;
            case R.id.tv_item_three:
                myViewPager.setCurrentItem(2);
                break;
            case R.id.tv_item_four:
                myViewPager.setCurrentItem(3);
                break;
            case R.id.home_search_cancel:
                onBackPressed();
                break;
            case R.id.home_search_emptied:
                cleanHistory();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // 先隐藏键盘
            ((InputMethodManager) mSearchEdt.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(HomeSearchActivity.this
                                    .getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
            save();
            keyword = mSearchEdt.getText().toString();
           if (adapter == null){
               mSearchHistory.setVisibility(View.GONE);
               mSearchAnswer.setVisibility(View.VISIBLE);
               adapter = new SearchKnowledgeAdapter(getSupportFragmentManager(), repository, keyword);
               myViewPager.setAdapter(adapter);
               checkedStatus(tv_item_one, tv_one_underline);
           }else{
               adapter.setDate(repository,keyword);
           }
            return true;
        }
        return false;
    }

    public void initSearchHistory() {
        String history = mSharedPreferences.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(history)) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            for (Object o : history.split(",")) {
                map = new HashMap<String, Object>();
                map.put("searchHistoryItem", (String) o);
                list.add(map);
            }
            data = list;
        }
        mSearchHistoryAdapter = new SearchHistoryAdapter(this, data);
        mSearchHistoryRecyclerView.setAdapter(mSearchHistoryAdapter);
        mSearchHistoryRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSearchEdt.setText((String) data.get(position).get("searchHistoryItem"));
                mSearchHistory.setVisibility(View.GONE);
            }
        });
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    public void cleanHistory() {
        mEditor.clear();
        data.clear();
        mSearchHistoryAdapter.notifyDataSetChanged();
        mSearchHistory.setVisibility(View.GONE);
        Toast.makeText(HomeSearchActivity.this, "清除搜索历史记录成功", Toast.LENGTH_SHORT).show();
    }

    public void save() {
        String text = mSearchEdt.getText().toString();
        String oldText = mSharedPreferences.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(text) && !oldText.contains(text)) {
            if (data.size() > 3) {
                Toast.makeText(HomeSearchActivity.this, "最多保存五条数据", Toast.LENGTH_SHORT).show();
                return;
            }
            mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, text + "," + oldText);
            mEditor.commit();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("searchHistoryItem", text);
            data.add(0, map);
        }
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String keyword = intent.getStringExtra(EXTRA_KEY_KEYWORD);
        if (!TextUtils.isEmpty(keyword)) {
            mSearchEdt.setText(keyword);
        }
    }


    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    checkedStatus(tv_item_one, tv_one_underline);
                    repository = "all";
                    adapter.setDate(repository, keyword);
                    break;
                case 1:
                    checkedStatus(tv_item_two, tv_two_underline);
                    repository = "A2";
                    adapter.setDate(repository, keyword);
                    break;
                case 2:
                    checkedStatus(tv_item_three, tv_three_underline);
                    repository = "B1";
                    adapter.setDate(repository, keyword);
                    break;
                case 3:
                    checkedStatus(tv_item_four, tv_four_underline);
                    repository = "B2";
                    adapter.setDate(repository, keyword);
                    break;
            }
        }
    }

    private void checkedStatus(TextView tv, TextView underline) {
        emptyStatus();
        tv.setTextSize(16);
        tv.setTextColor(Color.rgb(19, 56, 109));

        underline.setVisibility(View.VISIBLE);
    }

    private void emptyStatus() {
        tv_item_one.setTextSize(14);
        tv_item_one.setTextColor(Color.rgb(153, 153, 153));
        tv_one_underline.setVisibility(View.GONE);

        tv_item_two.setTextSize(14);
        tv_item_two.setTextColor(Color.rgb(153, 153, 153));
        tv_two_underline.setVisibility(View.GONE);

        tv_item_three.setTextSize(14);
        tv_item_three.setTextColor(Color.rgb(153, 153, 153));
        tv_three_underline.setVisibility(View.GONE);

        tv_item_four.setTextSize(14);
        tv_item_four.setTextColor(Color.rgb(153, 153, 153));
        tv_four_underline.setVisibility(View.GONE);

    }

}
