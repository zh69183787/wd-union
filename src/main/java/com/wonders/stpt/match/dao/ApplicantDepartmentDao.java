package com.wonders.stpt.match.dao;

import com.wonders.stpt.match.domain.MApplicantDepartment;
/**
 * 参赛单位
 * @author shanweifeng
 *
 */
public interface ApplicantDepartmentDao extends
		GenericDAO<MApplicantDepartment> {
    void deleteByPrizeId(String prizeId);
}
