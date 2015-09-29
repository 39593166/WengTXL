package ui_base.base_act;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import config.ResponseStatu;

/**
 * Created by Lenovo on 2015/8/24.
 */
public class BasePresenter {
    /**
     * 线程工作内容接口
     */
    public interface ThreadRunCall {
        public void work();
    }

    public Handler handler;//

    /**
     * 激活Handler处理handlerMessage
     */
    public void initHandler(final HandlerDeal handlerDeal) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handlerDeal.messageDeal(msg);
                super.handleMessage(msg);
            }
        };
    }

    /**
     * handler处理Message接口
     */
    public interface HandlerDeal {
        public void messageDeal(Message msg);
    }

    /**
     * 开启线程
     *
     * @param msgId
     * @param call
     */
    public void runInThread(int msgId, final ThreadRunCall call) {
        new Thread() {
            @Override
            public void run() {
                call.work();
                super.run();
            }
        }.start();
    }

    /**
     * get the returnning infomations
     * @param response
     * @return
     * @throws JSONException
     */
    public ResponseStatu getResponseStatu(String response) throws JSONException {
        JSONObject responseJson = new JSONObject(response);
        JSONObject responseInfo = responseJson.getJSONObject("responseInfo");
        Gson gson = new Gson();
       return gson.fromJson(responseInfo.toString(), ResponseStatu.class);
    }

    public JSONObject getResponseValue(String response) throws JSONException {
        JSONObject responseJson = new JSONObject(response);
        return responseJson.getJSONObject("response");
    }


    public static <T> T getBean(String jsonString, Class<T> cls)
            throws Exception {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            throw e;
        }
        return t;
    }
}
