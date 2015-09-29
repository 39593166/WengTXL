package com.wenkang.wengtxl.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.wenkang.wengtxl.view.UpdatePersonalInfoView;

import org.json.JSONException;

import config.Apis;
import config.ResponseStatu;
import entity.UserEntity;
import tool.net.ApplyCallback;
import tool.net.NetServer;
import ui_base.MyApplication;
import ui_base.base_act.BasePresenter;

/**
 * Created by Lenovo on 2015/9/23.
 */
public class UpdatePersonalPresenter extends BasePresenter {
    Context context;
    UpdatePersonalInfoView view;
    MyApplication app;

    public UpdatePersonalPresenter(Context context, UpdatePersonalInfoView view) {
        this.context = context;
        this.view = view;
        app = (MyApplication) context.getApplicationContext();
    }

    public void updatePhoneNum() {
        view.showProgressDialog("正在登陆，请稍后...");

        UserEntity userEntity = app.getUser();
        userEntity.setPhoneNum(view.getNewPhoneNum());
        NetServer.apply(Apis.updateUser(userEntity), new
                ApplyCallback() {
                    @Override
                    public void NetFailed(VolleyError error) {
                        view.updateFail("连接异常");
                        view.closeProgressDialog();
                    }

                    @Override
                    public void onReturned(String response) {
                        view.closeProgressDialog();
                        try {
                            ResponseStatu statu = getResponseStatu(response);
                            if (statu.getState() == 1) {
                                view.updateSuc();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    public String getNewPhoneNum() {
        return app.getUser().getPhoneNum();
    }
}
