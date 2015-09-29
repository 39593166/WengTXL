package com.wenkang.wengtxl.view;

import java.util.List;

import entity.DepartmentEntity;
import entity.UserEntity;
import ui_base.base_act.BaseView;

/**
 * Created by Lenovo on 2015/9/23.
 */
public interface ConnectorView extends BaseView {
    public void setDepartments(List<DepartmentEntity> departments);

    public void setUsers(List<UserEntity> users);

    public void goCheckPersonal(UserEntity userEntity);

    public void goChildDepartment(DepartmentEntity departmentEntity);
}
