package com.wonders.stpt.match.service.impl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.stpt.match.dao.MPersonalPrizeDao;
import com.wonders.stpt.match.domain.MPersonalPrize;
import com.wonders.stpt.match.service.IMPersonalPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
/**
 * 个人材料申报服务层方法
 * @author shanweifeng
 *
 */
@Service
public class MPersonalPrizeServiceImpl implements IMPersonalPrizeService {
	@Autowired
	private MPersonalPrizeDao mPersonalPrizeDao;
	@Override
	public PageList<MPersonalPrize> getMPersonalPrizes(
			MPersonalPrize personalPrize, Integer pageIndex, Integer pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		if(pageIndex==null || pageSize==null){
			return mPersonalPrizeDao.select(personalPrize, new PageBounds());
		}else{
			return mPersonalPrizeDao.select(personalPrize, new PageBounds(pageIndex, pageSize));
		}
	}

	@Override
	public PageList<MPersonalPrize> getMPersonalPrizes(MPersonalPrize personalPrize)
			throws Exception {
		// TODO Auto-generated method stub
		return getMPersonalPrizes(personalPrize,null,null);
	}

	@Override
	public MPersonalPrize getMPersonalPrize(String personalPrizeId)
			throws Exception {
		// TODO Auto-generated method stub
		MPersonalPrize personalPrize = new MPersonalPrize();
		personalPrize.setPersonalPrizeId(personalPrizeId);
		PageList<MPersonalPrize> personalPrizes=getMPersonalPrizes(personalPrize);
		if(personalPrizes!=null && personalPrizes.size()==1){
			return personalPrizes.get(0);
		}
		return null;
	}

	@Override
	public int delete(List<String> personalPrizeIds) throws Exception {
		// TODO Auto-generated method stub
		int i=0;//计数
		for(String personalPrizeId:personalPrizeIds){
			MPersonalPrize personalPrize = new MPersonalPrize();
			personalPrize.setRemoved("1");
			personalPrize.setPersonalPrizeId(personalPrizeId);
			update(personalPrize,null, true);//非动态更新（执行完后记录只剩下主键和removed属性有值）
			i++;
		}
		return 0;
	}

	@Override
	public int delete(String personalPrizeId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> personalPrizeIds = new ArrayList<String>();
		personalPrizeIds.add(personalPrizeId);
		return mPersonalPrizeDao.delete(personalPrizeIds);
	}

	@Override
	public void save(MPersonalPrize personalPrize)
			throws Exception {
		// TODO Auto-generated method stub
		mPersonalPrizeDao.insert(personalPrize);
	}

	@Override
	public void update(MPersonalPrize personalPrize, String[] excludes, boolean isDynamic)
			throws Exception {
		// TODO Auto-generated method stub
		MPersonalPrize person=new MPersonalPrize();
		person=getMPersonalPrize(personalPrize.getPersonalPrizeId());
		BeanUtils.copyProperties(personalPrize, person, excludes);
		mPersonalPrizeDao.update(person, isDynamic);
	}

}
