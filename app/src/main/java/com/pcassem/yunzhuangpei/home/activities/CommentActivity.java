package com.pcassem.yunzhuangpei.home.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.home.adapter.Comment;
import com.pcassem.yunzhuangpei.home.adapter.CommentListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mCommentContent;
    private TextView mCommentSend;

    private ListView mCommentList;
    private CommentListAdapter mCommentListAdapter;
    private List<Comment> data;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        initTouchEvent();
    }

    private void initView() {
        backIv = (ImageView) findViewById(R.id.back_iv);

        mCommentContent = (EditText) findViewById(R.id.comment_content);
        mCommentSend = (TextView) findViewById(R.id.comment_send);

        mCommentList = (ListView) findViewById(R.id.comment_list);

        data = new ArrayList<>();
        mCommentListAdapter = new CommentListAdapter(this, data);
        mCommentList.setAdapter(mCommentListAdapter);
    }

    private void initTouchEvent() {
        mCommentSend.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.comment_send:
                sendComment();
                break;
        }
    }


    public void sendComment(){
        if(mCommentContent.getText().toString().equals("")){
            Toast.makeText(CommentActivity.this, "评论不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            Comment comment = new Comment();
            comment.setName("评论者"+(data.size()+1));
            comment.setContent(mCommentContent.getText().toString());
            mCommentListAdapter.addComment(comment);
            // 发送完，清空输入框
            mCommentContent.setText("");

            Toast.makeText(CommentActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
