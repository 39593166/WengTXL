package ui_base;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import entity.UserEntity;
import tool.net.NetServer;

public class MyApplication extends Application {
    public String catchDirPath;
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        catchDirPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/kanggouonline";
        Log.e("缓存", catchDirPath);
        File file = new File(catchDirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        NetServer.init(this);
    }

}
