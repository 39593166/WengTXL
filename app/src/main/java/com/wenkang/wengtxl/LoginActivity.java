package com.wenkang.wengtxl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wenkang.wengtxl.presenter.LoginPresenter;
import com.wenkang.wengtxl.view.LoginView;

import ui_base.base_act.BaseActivity;


public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {
    LoginPresenter presenter;


    private EditText loginUserName;
    private EditText loginPassword;
    private Button loginSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_login);
        findViews();
        presenter = new LoginPresenter(this);
        presenter.setLoginInfo(this);

    }


    private void findViews() {
        initToolbar("登录");
        loginUserName = (EditText) findViewById(R.id.login_userName);
        loginPassword = (EditText) findViewById(R.id.login_password);
        loginSubmit = (Button) findViewById(R.id.login_submit);
        loginSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == loginSubmit) {
            presenter.login();
            presenter.saveUserLoginInfo(this);
        }
    }

    @Override
    public String getUserName() {
        return loginUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return loginPassword.getText().toString().trim();
    }

    @Override
    public void loginSuc() {
        presenter.saveUserInfo(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void loginFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserNameAndPassword(String userName, String password) {
        loginUserName.setText(userName);
        loginPassword.setText(password);
    }
}
