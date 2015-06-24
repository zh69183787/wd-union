package com.wonders.stpt.match.dao;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/9/5.
 */
public interface OrganizationDao  {
    List<Map> selectDepartments(String deptId)throws Exception;
    List<Map> selectSingleLeader(String deptId)throws Exception;
    List<Map> selectEmployee(String deptId)throws Exception;
}
