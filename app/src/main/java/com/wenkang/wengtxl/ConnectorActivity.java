package com.wenkang.wengtxl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.wenkang.wengtxl.presenter.ConnectorPresenter;
import com.wenkang.wengtxl.view.ConnectorView;

import java.util.List;

import adapter.CommonBaseAdapter;
import adapter.ViewHolderHelper;
import entity.DepartmentEntity;
import entity.UserEntity;
import noscroll.NoScrollListView;
import ui_base.base_act.BaseActivity;


public class ConnectorActivity extends BaseActivity implements ConnectorView {
    ConnectorPresenter presenter;
    CommonBaseAdapter<UserEntity> uAdapter;
    CommonBaseAdapter<DepartmentEntity> dAdapter;
    int departmentId = 0;
    String departmentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_connector);
        initActivity();
    }

    private void initActivity() {
        initToolbar(departmentName == null || departmentName.equals("") ? "组织架构" : departmentName);
        presenter = new ConnectorPresenter(this, this);
        departmentId = getIntent().getIntExtra("dId", 0);
        departmentName = getIntent().getStringExtra("dName");
        findViews();
        uAdapter = new CommonBaseAdapter<UserEntity>(this, R.layout.item_connector) {
            @Override
            public void convert(ViewHolderHelper helper, UserEntity item) {
                helper.setText(R.id.item_u_name, item.getRealName());
            }
        };
        dAdapter = new CommonBaseAdapter<DepartmentEntity>(this, R.layout.item_department) {
            @Override
            public void convert(ViewHolderHelper helper, DepartmentEntity item) {
                helper.setText(R.id.item_d_name, item.getDepartmentName());
            }
        };
        connectorUsers.setAdapter(uAdapter);
        connectorDepartments.setAdapter(dAdapter);
        presenter.getDepartmentChild(departmentId);
    }

    private NoScrollListView connectorUsers;
    private NoScrollListView connectorDepartments;

    private void findViews() {
        connectorUsers = (NoScrollListView) findViewById(R.id.connector_users);
        connectorDepartments = (NoScrollListView) findViewById(R.id.connector_departments);
        connectorUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goCheckPersonal(uAdapter.getItem(position));
            }
        });
        connectorDepartments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goChildDepartment(dAdapter.getItem(position));
            }
        });
    }

    @Override
    public void setDepartments(List<DepartmentEntity> departments) {
        dAdapter.resetData(departments);
    }

    @Override
    public void setUsers(List<UserEntity> users) {
        uAdapter.resetData(users);
    }

    @Override
    public void goCheckPersonal(UserEntity userEntity) {
        Intent intentPhone = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + userEntity.getPhoneNum()));
        startActivity(intentPhone);
    }

    @Override
    public void goChildDepartment(DepartmentEntity departmentEntity) {
        Intent intent = new Intent(this, ConnectorActivity.class);
        intent.putExtra("dId", departmentEntity.getId());
        intent.putExtra("dName", departmentName);
        startActivity(intent);
    }
}
