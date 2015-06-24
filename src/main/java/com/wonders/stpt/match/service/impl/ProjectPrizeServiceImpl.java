package com.wonders.stpt.match.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.stpt.match.dao.ProjectPrizeDao;
import com.wonders.stpt.match.domain.MProjectPrize;
import com.wonders.stpt.match.service.IProjectPrizeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
@Service
public class ProjectPrizeServiceImpl implements IProjectPrizeService {
	@Autowired
	private ProjectPrizeDao projectPrizeDao;
	@Override
	public PageList<MProjectPrize> getProjectPrizes(MProjectPrize projectPrize,
			Integer pageIndex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		if(pageIndex==null || pageSize == null)
			return projectPrizeDao.select(projectPrize, new PageBounds());
		else 
			return projectPrizeDao.select(projectPrize, new PageBounds(pageIndex, pageSize));
	}

	@Override
	public PageList<MProjectPrize> getProjectPrizes(MProjectPrize projectPrize)
			throws Exception {
		// TODO Auto-generated method stub
		return getProjectPrizes(projectPrize, null, null);
	}

	@Override
	public MProjectPrize getProjectPrize(String projectPrizeId)
			throws Exception {
		// TODO Auto-generated method stub
		MProjectPrize projectPrize=new MProjectPrize();
		projectPrize.setRemoved("0");
		projectPrize.setProjectPrizeId(projectPrizeId);
		PageList<MProjectPrize> projectPrizeList=getProjectPrizes(projectPrize);
		if(projectPrizeList!=null && projectPrizeList.size()==1)
			return projectPrizeList.get(0);
		return null;
	}

	@Override
	public int delete(List<String> projectPrizeIds) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		for(String projectPrizeId:projectPrizeIds){
			MProjectPrize projectPrize=new MProjectPrize();
			projectPrize.setRemoved("1");
			projectPrize.setProjectPrizeId(projectPrizeId);
			update(projectPrize, null, true);//动态更新
			i++;
		}
		return i;
	}

	@Override
	public int delete(String projectPrizeId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> projectPrizeIds=new ArrayList<String>();
		projectPrizeIds.add(projectPrizeId);
		return delete(projectPrizeIds);
	}

	@Override
	public void save(MProjectPrize projectPrize) throws Exception {
		// TODO Auto-generated method stub
		projectPrizeDao.insert(projectPrize);
	}

	@Override
	public void update(MProjectPrize projectPrize, String[] excludes,
			boolean isDynamic) throws Exception {
		// TODO Auto-generated method stub
		MProjectPrize project=new MProjectPrize();
		project=getProjectPrize(projectPrize.getProjectPrizeId());
		BeanUtils.copyProperties(projectPrize, project, excludes);
		projectPrizeDao.update(project, isDynamic);
	}

}
