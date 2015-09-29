package com.wenkang.wengtxl.presenter;

import android.content.Context;

import com.wenkang.wengtxl.view.MainView;

import entity.UserEntity;
import ui_base.MyApplication;

/**
 * Created by Lenovo on 2015/9/23.
 */
public class MainPresentor {
    MainView view;
    Context context;
    public MainPresentor(MainView view,Context context) {
        this.view = view;
        this.context = context;
    }

    public void goCompoly() {

    }

    public void goEdit() {

    }

    public void getUserInfo() {
        MyApplication app = (MyApplication) context.getApplicationContext();
        UserEntity userEntity = app.getUser();
        view.setUserInfo(userEntity);
    }
}
