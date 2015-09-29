package config;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

import tool.net.Request;

/**
 * Created by Lenovo on 2015/8/25.
 */
public class TxlRequest implements Request, Serializable {
    private static final long serialVersionUID = -5622180157099625593L;

    private ApiInfo apiInfo;
    private HashMap<String, Object> requestParams;

    public TxlRequest(int actionCode,int actionVersion) {
        this.apiInfo = new ApiInfo(actionCode,actionVersion);
    }

    public void putBussinessParams(String key, Object value) {
        if (requestParams == null) {
            requestParams = new HashMap<String, Object>();
        }
        requestParams.put(key, value);
    }

    @Override
    public int getMethod() {
        return Request.METHOD_POST;
    }

    @Override
    public String getUrl() {
        return ClientConfigs.APIHOST;
    }

    @Override
    public JSONObject getRequestParams() {
        Gson gson = new Gson();
        String param = gson.toJson(this);
        try {
            JSONObject jsonObject = new JSONObject(param);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
