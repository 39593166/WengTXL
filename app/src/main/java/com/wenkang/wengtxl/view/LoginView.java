package com.wenkang.wengtxl.view;

import ui_base.base_act.BaseView;

/**
 * Created by Lenovo on 2015/9/23.
 */
public interface LoginView extends BaseView{
    /**
     * 获取当前输入框的用户名
     *
     * @return
     */
    public String getUserName();

    /**
     * 获取当前输入框的密码
     *
     * @return
     */
    public String getPassword();

    /**
     * 登陆成功操作
     */
    public void loginSuc();

    /**
     * 登陆失败提示
     *
     * @param message
     */
    public void loginFail(String message);

    /**
     * 初始化输入框的用户名和密码
     *
     * @param userName
     * @param password
     */
    public void setUserNameAndPassword(String userName, String password);
}
