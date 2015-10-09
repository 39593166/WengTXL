package com.wenkang.wengtxl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wenkang.wengtxl.presenter.CheckPersonalPresenter;
import com.wenkang.wengtxl.view.CheckPersonalView;

import entity.UserEntity;
import ui_base.base_act.BaseActivity;

public class CheckPersonalActivity extends BaseActivity implements View.OnClickListener, CheckPersonalView {
    CheckPersonalPresenter personalPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_personal);
        UserEntity userEntity = (UserEntity) getIntent().getSerializableExtra("user");
        personalPresenter = new CheckPersonalPresenter(this, this, userEntity);
        findViews();
        setUserInfo(userEntity);
    }

    private TextView checkpersonalName;
    private TextView checkpersonalEmployeeNum;
    private TextView checkpersonalJob;
    private TextView checkpersonalTel;
    private Button checkpersonalCompany;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2015-10-09 14:59:58 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        checkpersonalName = (TextView) findViewById(R.id.checkpersonal_name);
        checkpersonalEmployeeNum = (TextView) findViewById(R.id.checkpersonal_employeeNum);
        checkpersonalJob = (TextView) findViewById(R.id.checkpersonal_job);
        checkpersonalTel = (TextView) findViewById(R.id.checkpersonal_tel);
        checkpersonalCompany = (Button) findViewById(R.id.checkpersonal_company);

        checkpersonalCompany.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == checkpersonalCompany) {
            personalPresenter.call();
        }
    }

    @Override
    public void setUserInfo(UserEntity userEntity) {
        this.checkpersonalName.setText(userEntity.getRealName());
        this.checkpersonalEmployeeNum.setText(userEntity.getWorkNum());
        this.checkpersonalJob.setText(userEntity.getJob());
        this.checkpersonalTel.setText(userEntity.getPhoneNum());
    }

    @Override
    public void call(String phoneNum) {
        Intent intentPhone = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + phoneNum));
        startActivity(intentPhone);
    }
}
