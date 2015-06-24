package com.wonders.stpt.match.service.impl;

import com.wonders.stpt.match.dao.ApplicantDepartmentDao;
import com.wonders.stpt.match.domain.MApplicantDepartment;
import com.wonders.stpt.match.service.IApplicantDepartmentService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/9/12.
 */
@Service
public class ApplicantDepartmentServiceImpl implements IApplicantDepartmentService {

    @Autowired
    private ApplicantDepartmentDao applicantDepartmentDao;

    @Override
    public void save(List<MApplicantDepartment> applicantDepartments) throws Exception {

        if(applicantDepartments != null && applicantDepartments.size()>0){
               deleteDepartmentInPrize(applicantDepartments.get(0).getPrizeId());
        }

        for(MApplicantDepartment applicantDepartment:applicantDepartments){
            applicantDepartmentDao.insert(applicantDepartment);
        }
    }

    @Override
    public void deleteDepartmentInPrize(String prizeId) throws Exception{
        applicantDepartmentDao.deleteByPrizeId(prizeId);
    }


    @Override
    public void delete(List<String> applicantDepartmentIds)throws Exception{
        applicantDepartmentDao.delete(applicantDepartmentIds);
    }

    @Override
    public List<MApplicantDepartment> getApplicantDepartment(String prizeId) throws Exception {
        MApplicantDepartment example = new MApplicantDepartment();
        example.setPrizeId(prizeId);

        return applicantDepartmentDao.select(example,new PageBounds() );
    }
}
