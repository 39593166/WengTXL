package com.wenkang.wengtxl.presenter;

import android.app.Activity;
import android.content.Context;

import com.android.volley.VolleyError;
import com.wenkang.wengtxl.view.LoginView;

import org.json.JSONException;

import config.Apis;
import config.ResponseStatu;
import entity.UserEntity;
import tool.net.ApplyCallback;
import tool.net.NetServer;
import ui_base.MyApplication;
import ui_base.MySharePerference;
import ui_base.base_act.BasePresenter;

/**
 * Created by Lenovo on 2015/9/23.
 */
public class LoginPresenter extends BasePresenter {
    LoginView view;
    private UserEntity user;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    /**
     * 登陆
     */
    public void login() {
        view.showProgressDialog("正在登陆，请稍后...");
        NetServer.apply(Apis.login(view.getUserName(), view.getPassword()), new
                ApplyCallback() {
                    @Override
                    public void NetFailed(VolleyError error) {
                        view.loginFail("连接异常");
                        view.closeProgressDialog();
                    }

                    @Override
                    public void onReturned(String response) {
                        view.closeProgressDialog();
                        try {
                            ResponseStatu statu = getResponseStatu(response);
                            if (statu.getState() == 1) {
                                user = getBean(getResponseValue(response).getJSONObject("user").toString(), UserEntity.class);
                                if (user != null) {
                                    view.loginSuc();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    /**
     * 保存用户登陆状态
     *
     * @param context
     */
    public void saveUserInfo(Activity context) {
        MyApplication app = (MyApplication) context.getApplication();
        app.setUser(user);
    }

    /**
     * 保存用户名和密码
     *
     * @param context
     */
    public void saveUserLoginInfo(Context context) {
        MySharePerference mySharePerference = new MySharePerference(context);
        mySharePerference.saveUserNameAndPassword(view.getUserName(), view.getPassword());
    }

    /**
     * 初始化用户名和密码
     *
     * @param context
     */
    public void setLoginInfo(Context context) {
        MySharePerference mySharePerference = new MySharePerference(context);
        String userName = mySharePerference.getUserName();
        String password = mySharePerference.getPassword();
        long lastLoginTime = mySharePerference.getLastLoginTime();
        long nowTime = System.currentTimeMillis();
        long max = 1000 * 60 * 60 * 24 * 30;
        if (nowTime - lastLoginTime < max) {
            view.setUserNameAndPassword(userName, password);
        } else {
            if (!userName.equals("")) {
                view.setUserNameAndPassword(userName, password);
            }
        }
//        if (userName != null && !userName.equals("") && password != null && !password.equals(""))
//            login();
    }
}
