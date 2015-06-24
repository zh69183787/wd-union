package com.wonders.stpt.match.service;

import com.wonders.stpt.match.domain.MApplicantDepartment;

import java.util.List;

/**
 * 参赛单位表
 * @author shanweifeng
 *
 */
public interface IApplicantDepartmentService {

    /**
     * 保存单位
     * @param applicantDepartments
     */
    void save(List<MApplicantDepartment> applicantDepartments)throws Exception;

    void deleteDepartmentInPrize(String prizeId) throws Exception;

    void delete(List<String> applicantDepartmentIds)throws Exception;

    List<MApplicantDepartment> getApplicantDepartment(String prizeId)throws Exception;
	
}
