package com.pcassem.yunzhuangpei.home.activities;

import android.animation.IntEvaluator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.home.adapter.Comment;
import com.pcassem.yunzhuangpei.home.adapter.CommentListAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCommentFrame;
    private EditText mCommentContent;
    private TextView mCommentSend;
    private LinearLayout mJumpCommentActivity;

    private RelativeLayout mLayoutCommentMenu;
    private RelativeLayout mlayoutCommentSay;

    private ImageView backIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        initView();
        initTouchEvent();
    }


    private void initView() {

        backIv = (ImageView) findViewById(R.id.back_iv);

        mCommentFrame = (TextView) findViewById(R.id.comment_frame);
        mCommentContent = (EditText) findViewById(R.id.comment_content);
        mCommentSend = (TextView) findViewById(R.id.comment_send);

        mJumpCommentActivity = (LinearLayout) findViewById(R.id.jump_comment);


        mLayoutCommentMenu  = (RelativeLayout) findViewById(R.id.layout_comment_menu);
        mlayoutCommentSay  = (RelativeLayout) findViewById(R.id.layout_comment_say);

    }

    private void initTouchEvent() {
        backIv.setOnClickListener(this);
        mCommentFrame.setOnClickListener(this);
        mCommentSend.setOnClickListener(this);
        mJumpCommentActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.comment_frame:
                mLayoutCommentMenu.setVisibility(View.GONE);
                mlayoutCommentSay.setVisibility(View.VISIBLE);
                break;
            case R.id.comment_send:
                sendComment();
                break;
            case R.id.jump_comment:
                Intent jumpCommentActivityIntent = new Intent(NewsDetailsActivity.this,CommentActivity.class);
                startActivity(jumpCommentActivityIntent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mlayoutCommentSay.getVisibility() == View.VISIBLE){
            mlayoutCommentSay.setVisibility(View.GONE);
            mLayoutCommentMenu.setVisibility(View.VISIBLE);
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void sendComment(){
        if(mCommentContent.getText().toString().equals("")){
            Toast.makeText(NewsDetailsActivity.this, "评论不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(NewsDetailsActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
