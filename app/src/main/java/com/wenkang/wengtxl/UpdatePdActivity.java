package com.wenkang.wengtxl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wenkang.wengtxl.presenter.UpdatePdPresenter;
import com.wenkang.wengtxl.view.UpdatePdView;

import tool.device.ToastUtil;
import ui_base.base_act.BaseActivity;

public class UpdatePdActivity extends BaseActivity implements UpdatePdView, View.OnClickListener {
    UpdatePdPresenter pdPresenter;
    private EditText editpd1;
    private EditText editpd2;
    private Button updatePdSubmit;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2015-10-09 14:28:37 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        editpd1 = (EditText)findViewById( R.id.editpd1 );
        editpd2 = (EditText)findViewById( R.id.editpd2 );
        updatePdSubmit = (Button)findViewById( R.id.updatePdSubmit );

        updatePdSubmit.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2015-10-09 14:28:37 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == updatePdSubmit ) {
            // Handle clicks for updatePdSubmit
            pdPresenter.updatePd();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setChildContentView(R.layout.activity_update_pd);
        findViews();
        pdPresenter = new UpdatePdPresenter(this,this);
    }

    @Override
    public String getNewPd() throws Exception {
        String pd1 = editpd1.getText().toString().trim();
        String pd2 = editpd2.getText().toString().trim();
        if(pd1.length()<6){
            throw new Exception("密码过短");
        }else if(!pd2.equals(pd1)){
            throw new Exception("密码输入不一致");
        }
        return pd1;
    }

    @Override
    public void showError(String msg) {
        ToastUtil.showGenericToast(this,msg);
    }

    @Override
    public void updateSuc() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}
