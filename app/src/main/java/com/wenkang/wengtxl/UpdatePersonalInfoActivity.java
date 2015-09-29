package com.wenkang.wengtxl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wenkang.wengtxl.presenter.UpdatePersonalPresenter;
import com.wenkang.wengtxl.view.UpdatePersonalInfoView;

import ui_base.base_act.BaseActivity;


public class UpdatePersonalInfoActivity extends BaseActivity implements UpdatePersonalInfoView, View.OnClickListener {
    UpdatePersonalPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_update_personal_info);
        presenter = new UpdatePersonalPresenter(this, this);
        findViews();
    }

    private EditText editphone;
    private Button updateSubmit;

    private void findViews() {
        initToolbar("Update PhoneNum");
        editphone = (EditText) findViewById(R.id.editphone);
        updateSubmit = (Button) findViewById(R.id.updateSubmit);

        updateSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == updateSubmit) {
            presenter.updatePhoneNum();
        }
    }

    @Override
    public String getNewPhoneNum() {
        return editphone.getText().toString();
    }

    @Override
    public void updateFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSuc() {
        Toast.makeText(this, "UpdateSuccess", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        // if (type.equals(choosedTopType)) {
        // intent.putExtra("topType", type);
        // } else {
        // intent.putExtra("topType", choosedTopType);
        intent.putExtra("phone", presenter.getNewPhoneNum());
        // }

        // intent.setClass(this, ClassifyGoodsListActivity.class);
        setResult(RESULT_OK, intent);
        this.finish();
    }
}
