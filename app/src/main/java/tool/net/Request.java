package tool.net;

import org.json.JSONObject;

public interface Request {
    public static final int METHOD_GET = 1;
    public static final int METHOD_POST = 2;

    public abstract int getMethod();

    public abstract String getUrl();

    public abstract JSONObject getRequestParams();
}
