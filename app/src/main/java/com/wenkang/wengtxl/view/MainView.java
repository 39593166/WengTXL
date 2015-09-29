package com.wenkang.wengtxl.view;

import entity.UserEntity;

/**
 * Created by Lenovo on 2015/9/23.
 */
public interface MainView {
    public void goGroup();

    public void editPersonalInfo();

    public void setUserInfo(UserEntity userEntity);
}
