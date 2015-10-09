package com.wenkang.wengtxl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckPersonalActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_personal);
        findViews();
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
        checkpersonalName = (TextView)findViewById( R.id.checkpersonal_name );
        checkpersonalEmployeeNum = (TextView)findViewById( R.id.checkpersonal_employeeNum );
        checkpersonalJob = (TextView)findViewById( R.id.checkpersonal_job );
        checkpersonalTel = (TextView)findViewById( R.id.checkpersonal_tel );
        checkpersonalCompany = (Button)findViewById( R.id.checkpersonal_company );

        checkpersonalCompany.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2015-10-09 14:59:58 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == checkpersonalCompany ) {
            // Handle clicks for checkpersonalCompany
        }
    }

}
