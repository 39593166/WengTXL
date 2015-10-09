package com.wenkang.wengtxl.presenter;

import android.content.Context;

import com.wenkang.wengtxl.view.CheckPersonalView;

import entity.UserEntity;
import ui_base.base_act.BasePresenter;

/**
 * Created by Lenovo on 2015/10/9.
 */
public class CheckPersonalPresenter extends BasePresenter {
    CheckPersonalView view;
    Context context;
    UserEntity userEntity;

    public CheckPersonalPresenter(CheckPersonalView view, Context context, UserEntity userEntity) {
        this.view = view;
        this.context = context;
        this.userEntity = userEntity;
    }

    public void call() {
        if (userEntity.getPhoneNum().length() < 8 || userEntity.getPhoneNum() == null || userEntity.getPhoneNum().equals("")) {
            view.errorMsg("没有有效的电话号码");
        } else
            view.call(userEntity.getPhoneNum());
    }
}
