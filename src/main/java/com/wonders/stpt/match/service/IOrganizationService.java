package com.wonders.stpt.match.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/9/5.
 */
public interface IOrganizationService {
    Map getDepartments(String deptId) throws Exception;

    Map getEmployee(String deptId) throws Exception;

    Map getDepartmentLeader(String deptId) throws Exception;
}
