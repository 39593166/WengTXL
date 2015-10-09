package com.wenkang.wengtxl.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenkang.wengtxl.view.ConnectorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import config.Apis;
import config.ResponseStatu;
import entity.DepartmentEntity;
import entity.UserEntity;
import tool.net.ApplyCallback;
import tool.net.NetServer;
import ui_base.base_act.BasePresenter;

/**
 * Created by Lenovo on 2015/9/23.
 */
public class ConnectorPresenter extends BasePresenter {
    Context context;
    ConnectorView view;

    public ConnectorPresenter(ConnectorView view, Context context) {
        this.view = view;
        this.context = context;
    }

    /**
     * get the department and  users
     *
     * @param departmentId
     */
    public void getDepartmentChild(int departmentId) {
        view.showProgressDialog("正在加载...");
        NetServer.apply(Apis.getDepartmentChild(departmentId), new
                ApplyCallback() {
                    @Override
                    public void NetFailed(VolleyError error) {
//                        view.loginFail("failed connect to server");
                        view.closeProgressDialog();
                    }

                    @Override
                    public void onReturned(String response) {
                        view.closeProgressDialog();
                        try {
                            ResponseStatu statu = getResponseStatu(response);
                            if (statu.getState() == 1) {
                                Gson gson = new Gson();
                                JSONObject val = getResponseValue(response);
                                JSONArray d = val.getJSONArray("departmentList");
                                JSONArray u = val.getJSONArray("userList");
                                List<DepartmentEntity> departmentEntityList = gson.fromJson(d.toString(), new TypeToken<List<DepartmentEntity>>() {
                                }.getType());
                                List<UserEntity> userEntitieList = gson.fromJson(u.toString(), new TypeToken<List<UserEntity>>() {
                                }.getType());
                                view.setDepartments(departmentEntityList);
                                view.setUsers(userEntitieList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}
