package com.wonders.stpt.match.service.impl;

import com.wonders.stpt.match.dao.OrganizationDao;
import com.wonders.stpt.match.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/9/5.
 */
@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public Map getDepartments(String deptId)  throws Exception{
        List<Map> list =  organizationDao.selectDepartments(deptId);
        Map root =  list.get(0);
        root.put("children",list.subList(1,list.size()-1));
        return root;
    }

    @Override
    public Map getEmployee(String deptId) throws Exception {
        List<Map> depts =  organizationDao.selectDepartments(deptId);
        Map root =  depts.get(0);
        root.put("children",organizationDao.selectEmployee(deptId));
        return root;
    }

    @Override
    public Map getDepartmentLeader(String deptId) throws Exception {
        List<Map> depts =  organizationDao.selectDepartments(deptId);
        Map root =  depts.get(0);
        root.put("children",organizationDao.selectSingleLeader(deptId));
        return root;
    }
}
