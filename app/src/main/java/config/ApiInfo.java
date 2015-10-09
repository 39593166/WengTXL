package config;

import java.io.Serializable;

/**
 * Created by Lenovo on 2015/8/25.
 */
public class ApiInfo implements Serializable {
    private static final long serialVersionUID = 6919617389581991991L;
    private int actionCode;
    private String osVersion;
    private String appVersion;
    private String actionVersion;

    public ApiInfo(int actionCode,String actionVersion) {
        this.actionCode = actionCode;
        this.actionVersion = actionVersion;
        this.osVersion="22";
        this.appVersion="1";
    }

    public int getActionCode() {
        return actionCode;
    }

    public void setActionCode(int actionCode) {
        this.actionCode = actionCode;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getActionVersion() {
        return actionVersion;
    }

    public void setActionVersion(String actionVersion) {
        this.actionVersion = actionVersion;
    }
}
