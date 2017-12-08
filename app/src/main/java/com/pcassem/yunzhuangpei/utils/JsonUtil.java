package com.pcassem.yunzhuangpei.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhangqi on 2017/12/7.
 */

public class JsonUtil {

    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String firstProcessString(Context context) {
        try {
            JSONObject object = new JSONObject(getJson("select.json", context));
            JSONObject object1 = (JSONObject) object.getJSONArray("nexts").get(0);
            return object1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String firstProblemAnswerString(Context context) {
        try {
            JSONObject object = new JSONObject(getJson("select.json", context));
            JSONObject object1 = (JSONObject) object.getJSONArray("nexts").get(1);
            return object1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String firstStandardString(Context context) {
        try {
            JSONObject object = new JSONObject(getJson("select.json", context));
            JSONObject object1 = (JSONObject) object.getJSONArray("nexts").get(2);
            return object1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String secondStandardString(Context context){
        try{
            JSONObject object = new JSONObject(firstStandardString(context));
            JSONObject object1 = (JSONObject) object.getJSONArray("nexts").get(0);
            return object1.toString();
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }



}
