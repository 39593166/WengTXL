package com.wenkang.wengtxl.view;

import ui_base.base_act.BaseView;

/**
 * Created by Lenovo on 2015/10/9.
 */
public interface UpdatePdView extends BaseView {
    public String getNewPd() throws Exception;

    public void showError(String msg);

    public void updateSuc();
}
