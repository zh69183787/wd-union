package com.wonders.stpt.match.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.stpt.match.dao.MTeamPrizeDao;
import com.wonders.stpt.match.domain.MTeamPrize;
import com.wonders.stpt.match.service.ITeamPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
@Service
public class TeamPrizeServiceImpl implements ITeamPrizeService {
	@Autowired
	private MTeamPrizeDao teamPrizeDao;
	@Override
	public PageList<MTeamPrize> getMTeamPrizes(MTeamPrize mTeamPrize,
			Integer pageIndex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		if(pageIndex==null || pageSize==null)
			return teamPrizeDao.select(mTeamPrize, new PageBounds());
		else 
			return teamPrizeDao.select(mTeamPrize, new PageBounds(pageIndex, pageSize));
	}

	@Override
	public PageList<MTeamPrize> getMTeamPrizes(MTeamPrize mTeamPrize)
			throws Exception {
		// TODO Auto-generated method stub
		return getMTeamPrizes(mTeamPrize,null,null);
	}

	@Override
	public MTeamPrize getMTeamPrize(String mTeamPrizeId) throws Exception {
		// TODO Auto-generated method stub
		MTeamPrize teamPrize=new MTeamPrize();
		teamPrize.setRemoved("0");
		teamPrize.setTeamPrizeId(mTeamPrizeId);
		PageList<MTeamPrize> teamList=getMTeamPrizes(teamPrize);
		if(teamList!=null&& teamList.size()==1)
			return teamList.get(0);
		return null;
	}

	@Override
	public int delete(List<String> mTeamPrizeIds) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		for(String teamPrizeId:mTeamPrizeIds){
			MTeamPrize teamPrize=new MTeamPrize();
			teamPrize.setRemoved("1");
			teamPrize.setTeamPrizeId(teamPrizeId);
			update(teamPrize,null,true);
			i++;
		}
		return i;
	}

	@Override
	public int delete(String mTeamPrizeId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> mTeamPrizeIds=new ArrayList<String>();
		mTeamPrizeIds.add(mTeamPrizeId);
		return delete(mTeamPrizeIds);
	}

	@Override
	public void save(MTeamPrize mTeamPrize) throws Exception {
		// TODO Auto-generated method stub
		teamPrizeDao.insert(mTeamPrize);
	}

	@Override
	public void update(MTeamPrize mTeamPrize, String[] excludes,
			boolean isDynamic) throws Exception {
		// TODO Auto-generated method stub
		MTeamPrize teamPrize=new MTeamPrize();
		teamPrize=getMTeamPrize(mTeamPrize.getTeamPrizeId());
		BeanUtils.copyProperties(mTeamPrize, teamPrize, excludes);
		teamPrizeDao.update(teamPrize, isDynamic);
	}

}
