package com.wenkang.wengtxl.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.wenkang.wengtxl.view.UpdatePdView;

import org.json.JSONException;

import config.Apis;
import config.ResponseStatu;
import entity.UserEntity;
import tool.net.ApplyCallback;
import tool.net.NetServer;
import ui_base.MyApplication;
import ui_base.base_act.BasePresenter;

/**
 * Created by Lenovo on 2015/10/9.
 */
public class UpdatePdPresenter extends BasePresenter {
    Context context;
    UpdatePdView view;
    MyApplication app;

    public UpdatePdPresenter(Context context, UpdatePdView view) {
        this.context = context;
        this.view = view;
        app = (MyApplication) context.getApplicationContext();
    }

    public void updatePd() {


        UserEntity userEntity = app.getUser();
        try {
           userEntity.setPassword(view.getNewPd());
            view.showProgressDialog("正在修改，请等待...");
            NetServer.apply(Apis.updateUser(userEntity), new
                    ApplyCallback() {
                        @Override
                        public void NetFailed(VolleyError error) {
                            view.showError("连接异常");
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
        } catch (Exception e) {
            e.printStackTrace();
            view.showError(e.getMessage());
        }

    }
}
