package ui_base.base_act;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wenkang.wengtxl.R;


/**
 * Created by Lenovo on 2015/8/24.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {
    LinearLayout childContentLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    /**
     * 设置Activity内容部分布局
     *
     * @param layoutId Activity布局id
     */
    protected void setChildContentView(int layoutId) {
        childContentLayout = (LinearLayout) this.findViewById(R.id.actLayout);
        View view = LayoutInflater.from(this).inflate(layoutId, null);
        childContentLayout.addView(view);
    }

    /**
     * 设置toolbar，需在所在activity里面添加Toolbar，并设置id为toolBar
     *
     * @param title toolbar显示的title
     */
    public void initToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources()
                .getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /**
     * 设置toolbar，需在所在activity里面添加Toolbar，并设置id为toolBar
     *
     * @param title toolbar显示的title
     */
    public void initToolbarNoBack(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources()
                .getColor(android.R.color.white));
        setSupportActionBar(toolbar);
    }
    // 提示进度框
    public Dialog progressDialog = null;
    // 对话框
    private AlertDialog.Builder builder;


    /**
     * 显示基本的对话框
     *
     * @param title
     * @param message
     */
    @Override
    public void showDialog(String title, String message,
                           final DialogWorkCallback callback) {
        builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.SubmitClick();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.QuitClick();
            }
        });
        builder.show();
    }


    @Override
    public void showProgressDialog(String content) {
        if (!this.isFinishing()) {

            if (progressDialog == null) {
                progressDialog = new AlertDialog.Builder(this).create();
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(true);
            }
            progressDialog.setTitle(content);
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }

    }

    @Override
    public void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
