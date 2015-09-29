package com.wenkang.wengtxl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wenkang.wengtxl.presenter.MainPresentor;
import com.wenkang.wengtxl.view.MainView;

import entity.UserEntity;
import ui_base.base_act.BaseActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener, MainView {
    private static final int UPDATE_PHONENUM = 1098;
    MainPresentor presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_main);
        findViews();
        presenter = new MainPresentor(this, this);
        presenter.getUserInfo();
    }

    private TextView mainpageName;
    private TextView mainpageEmployeeNum;
    private TextView mainpageJob;
    private TextView mainpageTel;
    private Button mainpageCompany;

    private void findViews() {
        initToolbarNoBack("MyInfo");
        mainpageName = (TextView) findViewById(R.id.mainpage_name);
        mainpageEmployeeNum = (TextView) findViewById(R.id.mainpage_employeeNum);
        mainpageJob = (TextView) findViewById(R.id.mainpage_job);
        mainpageTel = (TextView) findViewById(R.id.mainpage_tel);
        mainpageCompany = (Button) findViewById(R.id.mainpage_company);
        mainpageCompany.setOnClickListener(this);
        mainpageTel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mainpageCompany) {
            goGroup();
        } else if (v == mainpageTel) {
            editPersonalInfo();
        }
    }


    @Override
    public void goGroup() {
        Intent intent = new Intent(this, ConnectorActivity.class);
        startActivity(intent);
    }

    @Override
    public void editPersonalInfo() {
        Intent intent = new Intent(this, UpdatePersonalInfoActivity.class);
        startActivityForResult(intent, UPDATE_PHONENUM);
    }

    @Override
    public void setUserInfo(UserEntity userEntity) {
        this.mainpageName.setText(userEntity.getRealName());
        this.mainpageEmployeeNum.setText(userEntity.getWorkNum());
        this.mainpageJob.setText(userEntity.getJob());
        this.mainpageTel.setText(userEntity.getPhoneNum());
    }

    // /////////////////////按两次退出程序////////////////////
    private long mExitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();

        } else {
            finish();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case UPDATE_PHONENUM:
                    Bundle b = data.getExtras(); // data为B中回传的Intent
                    String phone = b.getString("phone");
                    this.mainpageTel.setText(phone);
                    break;
                default:
                    break;
            }
        }
    }
}
