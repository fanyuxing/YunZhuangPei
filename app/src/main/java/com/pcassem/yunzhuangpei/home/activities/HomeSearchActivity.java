package com.pcassem.yunzhuangpei.home.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.home.adapter.SearchHistoryAdapter;
import com.pcassem.yunzhuangpei.view.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeSearchActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    public static final String EXTRA_KEY_KEYWORD = "extra_key_keyword";
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";


    private EditText mSearchEdt;
    private TextView mCancelTv;
    private TextView mEmptiedTv;
    private ImageView mEditEmptiedIv;
    private ListView mSearchHistoryRecyclerView;
    private LinearLayout mSearchHistory;


    private SearchHistoryAdapter mSearchHistoryAdapter;
    private List<Map<String, Object>> data = null;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private String[] mVals = new String[] { "防水", "构件安装", "开裂", "管廊施工",
            "施工工艺", "节点处理" };
    private LayoutInflater mInflater;
    private FlowLayout mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("HomeSearchHistory", Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        data = new ArrayList<Map<String, Object>>();
        setContentView(R.layout.activity_home_search);

        initView();
        initTouchEvent();

        initSearchHistory();
        popularTagsData();
    }

    private void initView() {
        mInflater = LayoutInflater.from(this);

        mSearchEdt = (EditText) findViewById(R.id.home_search_edt);
        mEditEmptiedIv = (ImageView) findViewById(R.id.home_search_edit_emptied);
        mCancelTv = (TextView) findViewById(R.id.home_search_cancel);
        mEmptiedTv = (TextView) findViewById(R.id.home_search_emptied);
        mSearchHistory = (LinearLayout) findViewById(R.id.home_search_history);
        mSearchHistoryRecyclerView = (ListView) findViewById(R.id.home_searchhistory_recyclerview);
        mFlowLayout = (FlowLayout) findViewById(R.id.flowlayout_popular_tags);

        mSearchEdt.requestFocus();

        mEditEmptiedIv.setOnClickListener(this);
        mCancelTv.setOnClickListener(this);
        mEmptiedTv.setOnClickListener(this);

    }

    private void popularTagsData(){
        for (int i = 0; i < mVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(R.layout.item_popular_tags_search, mFlowLayout, false);
            tv.setText(mVals[i]);
            //添加到父View
            mFlowLayout.addView(tv);
        }
    }

    private void initTouchEvent() {

        //点击软键盘回车键触发
        mSearchEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return true;
                }
                return false;
            }
        });

        mSearchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mCancelTv.setText("搜索");
                    mEditEmptiedIv.setVisibility(View.GONE);
                    if (data.size() > 0) {
                        mSearchHistory.setVisibility(View.VISIBLE);
                    } else {
                        mSearchHistory.setVisibility(View.GONE);
                    }
                } else {
                    mSearchHistory.setVisibility(View.GONE);
                    mCancelTv.setText("搜索");
                    mEditEmptiedIv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
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

        if (data.size() > 0) {
            mSearchHistory.setVisibility(View.VISIBLE);
        } else {
            mSearchHistory.setVisibility(View.GONE);
        }

        mSearchHistoryAdapter = new SearchHistoryAdapter(this,data);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_edit_emptied:
                mSearchEdt.setText("");
                v.setVisibility(View.GONE);
                break;
            case R.id.home_search_cancel:
                if (mSearchEdt.getText().length() > 0) {
                    save();
                } else {
                    Toast.makeText(this, "搜索框为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.home_search_emptied:
                cleanHistory();
                break;
        }
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
