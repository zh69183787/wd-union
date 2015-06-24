package com.wonders.stpt.match.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonders.stpt.match.dao.ProjectAchievementDao;
import com.wonders.stpt.match.domain.MProjectAchievement;
import com.wonders.stpt.match.service.IProjectAchievementService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;
@Service
public class ProjectAchievementServiceImpl implements
		IProjectAchievementService {
	@Autowired
	private ProjectAchievementDao projectAchievementDao;
	@Override
	public PageList<MProjectAchievement> getProjectAchievements(
			MProjectAchievement projectAchievement, Integer pageIndex,
			Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		if(pageIndex == null && pageSize == null)
			return projectAchievementDao.select(projectAchievement, new PageBounds());
		else 
			return projectAchievementDao.select(projectAchievement, new PageBounds(pageIndex, pageSize));
	}

	@Override
	public PageList<MProjectAchievement> getProjectAchievements(
			MProjectAchievement projectAchievement) throws Exception {
		// TODO Auto-generated method stub
		return getProjectAchievements(projectAchievement, null, null);
	}

	@Override
	public MProjectAchievement getProjectAchievement(String projectAchievementId)
			throws Exception {
		// TODO Auto-generated method stub
		MProjectAchievement project=new MProjectAchievement();
		project.setRemoved("0");
		project.setProjectAchievementId(projectAchievementId);
		PageList<MProjectAchievement> projectAchievementList=getProjectAchievements(project);
		if(projectAchievementList != null && projectAchievementList.size()==1)
			return projectAchievementList.get(0);
		return null;
	}

	@Override
	public int delete(List<String> projectAchievementIds) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		for(String projectAchievementId:projectAchievementIds){
			MProjectAchievement project=new MProjectAchievement();
			project.setRemoved("1");
			project.setProjectAchievementId(projectAchievementId);
			update(project, null, true);
			i++;
		}
		return i;
	}

	@Override
	public int delete(String projectAchievementId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> projectAchievementIds=new ArrayList<String>();
		projectAchievementIds.add(projectAchievementId);
		return delete(projectAchievementId);
	}

	@Override
	public void save(MProjectAchievement projectAchievement) throws Exception {
		// TODO Auto-generated method stub
		projectAchievementDao.insert(projectAchievement);
	}

	@Override
	public void update(MProjectAchievement projectAchievement,
			String[] excludes, boolean isDynamic) throws Exception {
		// TODO Auto-generated method stub
		MProjectAchievement project=new MProjectAchievement();
		project=getProjectAchievement(projectAchievement.getProjectAchievementId());
		BeanUtils.copyProperties(projectAchievement, project, excludes);
		projectAchievementDao.update(project, isDynamic);
	}

}
