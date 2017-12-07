package com.pcassem.yunzhuangpei.training.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.training.fragments.SignUpDialogFragment;

public class CourseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCourseSignUp;

    private ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        initView();
        initTouceEvent();

    }

    private void initView(){
        mCourseSignUp = (Button) findViewById(R.id.course_sign_up);
        backIv = (ImageView) findViewById(R.id.back_iv);
    }

    private void initTouceEvent(){
        mCourseSignUp.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.course_sign_up:
                SignUpDialogFragment.newInstance().show(getSupportFragmentManager(),"dialog");
                break;
        }
    }

}
