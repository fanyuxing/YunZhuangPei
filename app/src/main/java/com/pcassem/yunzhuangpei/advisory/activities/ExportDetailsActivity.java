package com.pcassem.yunzhuangpei.advisory.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.presenter.ExportDetailsPresenter;
import com.pcassem.yunzhuangpei.advisory.view.ExportDetailsView;
import com.pcassem.yunzhuangpei.entity.ExportDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.UserEntity;
import com.pcassem.yunzhuangpei.home.presenter.NewsDetailsPresenter;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.view.CommomDialog;
import com.pcassem.yunzhuangpei.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import io.rong.callkit.RongCallKit;

import static com.pcassem.yunzhuangpei.R.style.dialog;

public class ExportDetailsActivity extends AppCompatActivity implements ExportDetailsView, View.OnClickListener {

    public static final String TAG = "ExportDetailsActivity";

    private ImageView backIv;

    private SimpleDraweeView mIcon;
    private TextView mName;
    private TextView mCompany;
    private TextView mTitle;
    private TextView mAddress;
    private TextView mDescription;
    private TextView mBusiness;
    private Button mIsOnline;
    private Button mIsBook;
    private FlowLayout mDomains;
    private TextView mAnswerCount;
    private TextView mBookCount;
    private TextView mOnlineCount;
    private TextView mArticleCount;

    private int isOnline;
    private int isBook;

    private String phoneNumber;

    private LayoutInflater mInflater;
    private List<String> mVals;
    private ExportDetailsPresenter mExportDetailsPresenter;
    private ExportDetailsEntity mData;
    private int specialistID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_details);
        initView();
        initTouchEvent();

        mVals = new ArrayList<>();

        mExportDetailsPresenter = new ExportDetailsPresenter(this);
        mExportDetailsPresenter.onCreate();
        specialistID = getIntent().getIntExtra("specialistID", -1);
        mExportDetailsPresenter.getExportDetails(specialistID);
    }

    private void initView(){
        mInflater = LayoutInflater.from(this);
        backIv = (ImageView) findViewById(R.id.back_iv);
        mIcon = (SimpleDraweeView) findViewById(R.id.icon);
        mName = (TextView) findViewById(R.id.name);
        mCompany = (TextView) findViewById(R.id.company);
        mTitle = (TextView) findViewById(R.id.title);
        mAddress = (TextView) findViewById(R.id.address);
        mDescription = (TextView) findViewById(R.id.description);
        mBusiness = (TextView) findViewById(R.id.business);
        mIsOnline = (Button) findViewById(R.id.is_online);
        mIsBook = (Button) findViewById(R.id.is_book);
        mDomains = (FlowLayout) findViewById(R.id.domains);
        mAnswerCount = (TextView) findViewById(R.id.answer_count);
        mBookCount = (TextView) findViewById(R.id.book_count);
        mOnlineCount = (TextView) findViewById(R.id.online_count);
        mArticleCount = (TextView) findViewById(R.id.article_count);
    }

    private void initTouchEvent(){
        backIv.setOnClickListener(this);
        mIsOnline.setOnClickListener(this);
    }

    private void popularTagsData(){
        for (int i = 0; i < mVals.size(); i++) {
            TextView tv = (TextView) mInflater.inflate(
                    R.layout.item_advisory_export_tags, mDomains, false);
            tv.setText(mVals.get(i));
            //添加到父View
            mDomains.addView(tv);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.is_online:
                String username = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getString("username","");
                if (username == ""){
                    new CommomDialog(this, R.style.commom_dialog, "尚未登录，是否先登录？", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                Intent intent = new Intent(ExportDetailsActivity.this, LoginActivity.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }

                        }
                    }).setTitle("提示信息").show();
                }else{
                    RongCallKit.startSingleCall(ExportDetailsActivity.this, phoneNumber , RongCallKit.CallMediaType.CALL_MEDIA_TYPE_VIDEO);
                }
                break;
        }
    }

    @Override
    public void onSuccess(ResultListEntity<ExportDetailsEntity> exportDetails) {
        mData = exportDetails.getResult().get(0);

        phoneNumber = mData.getPhoneNumber();

        String url = mData.getIcon();
        mIcon.setImageURI(Uri.parse(url));
        mName.setText(mData.getName());
        mCompany.setText(mData.getCompany());
        mTitle.setText(mData.getTitle());
        mAddress.setText("工作地点："+mData.getAddress());
        mDescription.setText(mData.getDescription());
        mBusiness.setText(mData.getBusiness());
        mVals = mData.getDomains();
        popularTagsData();

        isOnline = mData.getIsOnline();
        isBook = mData.getIsBook();

        if (isOnline == 1){
            mIsOnline.setBackgroundResource(R.drawable.submit_style);
        }else {
            mIsOnline.setBackgroundResource(R.drawable.submit_non_style);
            mIsOnline.setEnabled(false);
        }

        if (isBook == 1){
            mIsBook.setBackgroundResource(R.drawable.submit_style);
        }else {
            mIsBook.setBackgroundResource(R.drawable.submit_non_style);
            mIsBook.setEnabled(false);
        }

        mAnswerCount.setText(mData.getAnswerCount() + "次");
        mBookCount.setText(mData.getBookCount() + "次");
        mOnlineCount.setText(mData.getOnlineCount() + "次");
        mArticleCount.setText(mData.getArticleCount() + "篇");

    }

    @Override
    public void onError() {

    }
}
