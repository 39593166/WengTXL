package com.wenkang.wengtxl.view;

import ui_base.base_act.BaseView;

/**
 * Created by Lenovo on 2015/9/23.
 */
public interface UpdatePersonalInfoView extends BaseView {
    public String getNewPhoneNum();

    public void updateFail(String msg);

    public void updateSuc();
}
