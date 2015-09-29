package tool.net;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NetServer {
    private static RequestQueue mQueue;
    private static NetServer net;

    public static void init(Context ctx) {
        if (net == null) {
            net = new NetServer();
        }
        mQueue = Volley.newRequestQueue(ctx);
    }

    private NetServer() {
    }

    public static void apply(Request re, ApplyCallback callback) {
        if (re.getMethod() == Request.METHOD_GET) {
            Log.e("url", re.getUrl());
            get(re.getUrl(), callback);
        } else if (re.getMethod() == Request.METHOD_POST) {
            Log.e("请求地址：", re.getUrl());
            Log.e("请求参数：", re.getRequestParams().toString());
            post(re.getUrl(), re.getRequestParams(), callback);
        }
    }

    public static void uploadImage(int userId, String url, String filePath,
                                   final ApplyCallback callback) {
        File file = ImageSubTool.pressPic(filePath, 800, 800);
        HttpUtils hu = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("file", file);
        hu.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onFailure(HttpException arg0, String arg1) {
                callback.NetFailed(null);
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                callback.onReturned(responseInfo.result);
            }
        });

    }

    protected static void get(String url,
                              final ApplyCallback callback) {

        StringRequest mStringRequest = new StringRequest(url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("请求结果:", response);
                        callback.onReturned(response);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("请求错误:" + error.toString());
                callback.NetFailed(error);
            }

        });
        mQueue.add(mStringRequest);

    }

    protected static void post(String url,
                               JSONObject jsonObjectParams, final ApplyCallback callback) {
        if (jsonObjectParams == null) {
            Log.e("requestParams", "null");
        } else {
            Log.e("requestParams", jsonObjectParams.toString());
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Method.POST, url, jsonObjectParams,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onReturned(response.toString());
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.NetFailed(error);
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Connection", "Keep-Alive");
                return headers;
            }
        };
        mQueue.add(jsonObjectRequest);
    }

    protected static void post(String url,
                               String stringObjectParams, final ApplyCallback callback) {
        if (stringObjectParams == null) {
            Log.e("requestParams", "null");
        } else {
            Log.e("requestParams", stringObjectParams);
        }
        MyStringRequest stringRequest = new MyStringRequest(Method.POST, url, stringObjectParams, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.onReturned(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.NetFailed(error);
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Connection", "Keep-Alive");
                return headers;
            }
        };
        mQueue.add(stringRequest);
    }
}
