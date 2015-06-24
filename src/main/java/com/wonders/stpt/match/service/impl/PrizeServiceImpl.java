package com.wonders.stpt.match.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.match.dao.PrizeRuleDao;
import com.wonders.stpt.match.domain.PrizeRule;
import com.wonders.stpt.match.service.IApplicantDepartmentService;
import com.wonders.stpt.match.service.IPrizeRuleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.stpt.match.dao.ApplicantDepartmentDao;
import com.wonders.stpt.match.dao.PrizeDao;
import com.wonders.stpt.match.domain.MApplicantDepartment;
import com.wonders.stpt.match.domain.MPrize;
import com.wonders.stpt.match.service.IPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
/**
 * 奖项
 * @author shanweifeng
 *
 */
@Service
public class PrizeServiceImpl implements IPrizeService {


	@Autowired
	private PrizeDao prizeDao;

    @Autowired
    private IPrizeRuleService prizeRuleService;

    @Autowired
    private IApplicantDepartmentService applicantDepartmentService;

	@Override
	public PageList<MPrize> getPrizes(MPrize prize, Integer pageIndex,
			Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		if(pageIndex ==null || pageSize == null)
			return prizeDao.select(prize, new PageBounds());
		else
			return prizeDao.select(prize, new PageBounds(pageIndex, pageSize));
	}

	@Override
	public PageList<MPrize> getPrizes(MPrize prize) throws Exception {
		// TODO Auto-generated method stub
		return getPrizes(prize,null,null);
	}

	@Override
	public MPrize getPrize(String prizeId) throws Exception {
		// TODO Auto-generated method stub
		MPrize prize=new MPrize();
		prize.setRemoved("0");
		prize.setPrizeId(prizeId);
		PageList<MPrize> prizeList=getPrizes(prize);
		if(prizeList!=null && prizeList.size()==1)
			return prizeList.get(0);
		return null;
	}

	@Override
	public int delete(List<String> prizeIds) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		for(String prizeId:prizeIds){
			MPrize prize=new MPrize();
			prize.setRemoved(MPrize.DELETE);
			prize.setPrizeId(prizeId);
			update(prize,true);
			i++;
		}
		return i;
	}

	@Override
	public int delete(String prizeId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> prizeIds=new ArrayList<String>();
		prizeIds.add(prizeId);
		return delete(prizeIds);
	}

	@Override
	public void save(MPrize prize,String[] excludes) throws Exception {
        prizeRuleService.validRule(prize);
		if(StringUtils.isNotBlank(prize.getPrizeId())){
            MPrize pPrize=getPrize(prize.getPrizeId());
            BeanUtils.copyProperties(prize, pPrize, excludes);
            prizeDao.update(pPrize, false);

        }else{

            prizeDao.insert(prize);
        }
        if(prize.getApplicationDepartmentList()!=null){

            for (MApplicantDepartment mApplicantDepartment : prize.getApplicationDepartmentList()) {
                mApplicantDepartment.setPrizeId(prize.getPrizeId());
            }
            applicantDepartmentService.save(prize.getApplicationDepartmentList());
        }
	}

	@Override
	public void update(MPrize prize, boolean isDynamic)
			throws Exception {
		prizeDao.update(prize, isDynamic);
	}



}
