package com.pcassem.yunzhuangpei.home.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.activities.ExportAnswerActivity;
import com.pcassem.yunzhuangpei.advisory.activities.SiteGuideActivity;
import com.pcassem.yunzhuangpei.advisory.activities.SpeedAskActivity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.VoiceBean;
import com.pcassem.yunzhuangpei.home.activities.CameraActivity;
import com.pcassem.yunzhuangpei.home.view.NewsView;
import com.pcassem.yunzhuangpei.home.activities.AllNewsActivity;
import com.pcassem.yunzhuangpei.home.activities.HomeSearchActivity;
import com.pcassem.yunzhuangpei.home.activities.NewsDetailsActivity;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;
import com.pcassem.yunzhuangpei.home.presenter.NewsPresenter;
import com.pcassem.yunzhuangpei.training.activities.KnowledgeActivity;
import com.pcassem.yunzhuangpei.training.activities.TrainingCoursesActivity;
import com.pcassem.yunzhuangpei.view.MyDividerItemDecoration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements View.OnClickListener, NewsView, LatestNewsAdapter.OnItemClickListener {


    public static final String TAG = "HomeFragment";
    public static final int TAKE_PHOTO = 1;
    private static final int RESULT_CODE_STARTCAMERA = 1;
    private static final int RESULT_CODE_STARTAUDIO = 2;
    private Uri imageUri;

    private TextView mHomeSearchBtn;
    private TextView mAllNewsJumpBtn;
    private ImageView mVoice;
    private ImageView mCamera;

    private Button button1;
    private Button button2;

    private LinearLayout button3;
    private LinearLayout button4;
    private LinearLayout button5;
    private LinearLayout button6;

    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mLatestNewsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<NewsEntity> mNewsData;
    private NewsPresenter newsPresenter;
    private StringBuffer mBuffer;


    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);
        initTouchEvent();

        ImageView homeImage = (ImageView) view.findViewById(R.id.home_image);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float screenWidth = dm.widthPixels;
        int height = (int) ((687 / 1098.0) * screenWidth);

        ViewGroup.LayoutParams para = homeImage.getLayoutParams();
        para.height = height;
        homeImage.setLayoutParams(para);

        mBuffer = new StringBuffer();
        mNewsData = new ArrayList<>();
        // 设置布局管理器
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 设置adapter
        newsPresenter = new NewsPresenter(this);
        newsPresenter.onCreate();
        newsPresenter.getLatestNewsList();

        return view;
    }


    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mHomeSearchBtn = (TextView) view.findViewById(R.id.home_search_btn);
        mVoice = (ImageView) view.findViewById(R.id.voice);
        mCamera = (ImageView) view.findViewById(R.id.camera);
        mAllNewsJumpBtn = (TextView) view.findViewById(R.id.jump_all_news_activity);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);

        button3 = (LinearLayout) view.findViewById(R.id.button3);
        button4 = (LinearLayout) view.findViewById(R.id.button4);
        button5 = (LinearLayout) view.findViewById(R.id.button5);
        button6 = (LinearLayout) view.findViewById(R.id.button6);
    }

    private void initTouchEvent() {

        mHomeSearchBtn.setOnClickListener(this);
        mAllNewsJumpBtn.setOnClickListener(this);
        mVoice.setOnClickListener(this);
        mCamera.setOnClickListener(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_search_btn:
                Intent searchIntent = new Intent(getActivity(), HomeSearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("searchID", 0);
                searchIntent.putExtras(bundle);
                startActivity(searchIntent);
                break;
            case R.id.voice:
                if (PackageManager.PERMISSION_GRANTED ==   ContextCompat.
                        checkSelfPermission(getActivity(), android.Manifest.permission.RECORD_AUDIO)) {
                    voice();
                }else{
                    //提示用户开户权限音频
                    String[] perms = {"android.permission.RECORD_AUDIO"};
                    ActivityCompat.requestPermissions(getActivity(),perms, RESULT_CODE_STARTAUDIO);
                }
                break;
            case R.id.camera:
                if (PackageManager.PERMISSION_GRANTED ==   ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA)) {
                    camera();
                }else{
                    //提示用户开户权限
                    String[] perms = {"android.permission.CAMERA"};
                    ActivityCompat.requestPermissions(getActivity(),perms, RESULT_CODE_STARTCAMERA);
                }
                break;
            case R.id.jump_all_news_activity:
                Intent jumpAllNewsIntent = new Intent(getActivity(), AllNewsActivity.class);
                startActivity(jumpAllNewsIntent);
                break;
            case R.id.button1:
                Intent intent = new Intent(getActivity(), SpeedAskActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(getActivity(), TrainingCoursesActivity.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(getActivity(), KnowledgeActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("selectId", "0");
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(getActivity(), KnowledgeActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("selectId", "1");
                intent3.putExtras(bundle1);
                startActivity(intent3);
                break;
            case R.id.button5:
                Intent intent4 = new Intent(getActivity(), ExportAnswerActivity.class);
                startActivity(intent4);
                break;
            case R.id.button6:
                Intent intent5 = new Intent(getActivity(), SiteGuideActivity.class);
                startActivity(intent5);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent(getActivity(), CameraActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("imageUri", imageUri.toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch(permsRequestCode){
            case RESULT_CODE_STARTCAMERA:
                boolean cameraAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                if(cameraAccepted){
                    camera();
                }else{
                    Toast.makeText(getActivity(), "请开启应用拍照权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case RESULT_CODE_STARTAUDIO:
                boolean albumAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                if(albumAccepted){
                    voice();
                }else{
                    Toast.makeText(getActivity(), "请开启音频权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //解析数据
    private String parseDatd(String json) {
        //创建Gson对象
        Gson gson = new Gson();
        VoiceBean voiceBean = gson.fromJson(json, VoiceBean.class);

        StringBuffer sb = new StringBuffer();

        List<VoiceBean.WsBean> ws = voiceBean.getWs();

        for (VoiceBean.WsBean wsBean : ws) {
            String word = wsBean.getCw().get(0).getW();
            sb.append(word);
        }
        return sb.toString();
    }

    //点击最新资讯进入单个资讯
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("newsID", position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSuccess(ResultListEntity<NewsEntity> newsListEntity) {
        mNewsData = newsListEntity.getResult();
        if (mNewsData == null) {
            Toast.makeText(getContext(), "无数据", Toast.LENGTH_SHORT).show();
        }
        if (mLatestNewsAdapter == null) {
            mLatestNewsAdapter = new LatestNewsAdapter(mNewsData);
            mRecyclerView.setAdapter(mLatestNewsAdapter);
            mLatestNewsAdapter.setmOnItemClickListener(this);
        }
        mLatestNewsAdapter.setmData(mNewsData);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "网络出错", Toast.LENGTH_SHORT).show();
    }

    private void camera(){
        // 创建File对象，用于存储拍照后的图片
        File outputImage = new File(getActivity().getExternalCacheDir(), System.currentTimeMillis() + "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(getActivity(), "com.example" + ".fileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        // 启动相机程序
        Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");
        camera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(camera, TAKE_PHOTO);
    }

    private void voice(){
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(getActivity(), null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult result, boolean isLast) {
                mBuffer.append(parseDatd(result.getResultString()));
                if (isLast) {
                    Intent intent = new Intent(getActivity(), HomeSearchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("voice", mBuffer.toString());
                    bundle.putInt("searchID", 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();
    }

}
