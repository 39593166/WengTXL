package ui_base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Date;

public class MySharePerference {
    private final String USERNAME = "userName";
    private final String PASSWORD = "password";
    private final String LOGINTIME = "loginTime";
    private SharedPreferences preferences;
    Context context;

    public MySharePerference(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("kanggou",
                Context.MODE_PRIVATE);
    }

    /**
     * 保存用户名和密码
     *
     * @param userName
     * @param Password
     */
    public void saveUserNameAndPassword(String userName, String Password) {
        Date date = new Date();
        long lastLoginTime = System.currentTimeMillis();
        Editor editor = preferences.edit();
        editor.putString(USERNAME, userName);
        editor.putString(PASSWORD, Password);
        editor.putLong(LOGINTIME, lastLoginTime);
        editor.commit();
    }

    /**
     * 登陆用户名
     *
     * @return
     */
    public String getUserName() {
        return preferences.getString(USERNAME, "");
    }

    /**
     * 登陆密码
     *
     * @return
     */
    public String getPassword() {
        return preferences.getString(PASSWORD, "");
    }

    /**
     * 最后登陆时间
     * @return
     */
    public long getLastLoginTime() {
        return preferences.getLong(LOGINTIME, System.currentTimeMillis());
    }
}
