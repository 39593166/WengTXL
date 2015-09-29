package config;


import entity.UserEntity;
import tool.net.Request;

/**
 * Created by Lenovo on 2015/8/25.
 */
public class Apis {

    /**
     * 用户登陆
     *
     * @param userName
     * @param password
     * @return
     */
    public static Request login(String userName, String password) {
        TxlRequest request = new TxlRequest(ActionCodeConfiguration.LOGIN, 1);
        request.putBussinessParams("workerNum", userName);
        request.putBussinessParams("password", password);
        return request;
    }

    public static Request getDepartmentChild(int id) {
        TxlRequest request = new TxlRequest(ActionCodeConfiguration.GET_DEPARTMENTCHILD, 1);
        request.putBussinessParams("pDepartmentId", id);
        return request;
    }

    public static Request updateUser(UserEntity userEntity) {
        TxlRequest request = new TxlRequest(ActionCodeConfiguration.UPDATE_INFO, 1);
        request.putBussinessParams("user", userEntity);
        return request;
    }
}
