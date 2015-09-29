package config;

import java.io.Serializable;

/**
 * Created by Lenovo on 2015/8/25.
 */
public class ApiInfo implements Serializable {
    private static final long serialVersionUID = 6919617389581991991L;
    private int actionCode;
    private int osVersion;
    private int appVersion;
    private int actionVersion;

    public ApiInfo(int actionCode,int actionVersion) {
        this.actionCode = actionCode;
        this.actionVersion = actionVersion;
    }

    public int getActionCode() {
        return actionCode;
    }

    public void setActionCode(int actionCode) {
        this.actionCode = actionCode;
    }

    public int getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(int osVersion) {
        this.osVersion = osVersion;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public int getActionVersion() {
        return actionVersion;
    }

    public void setActionVersion(int actionVersion) {
        this.actionVersion = actionVersion;
    }
}
