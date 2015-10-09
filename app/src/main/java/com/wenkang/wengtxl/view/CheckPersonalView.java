package com.wenkang.wengtxl.view;

import entity.UserEntity;
import ui_base.base_act.BaseView;

/**
 * Created by Lenovo on 2015/10/9.
 */
public interface CheckPersonalView extends BaseView {

    public void setUserInfo(UserEntity userInfo);

    public void call(String phoneNum);

}
