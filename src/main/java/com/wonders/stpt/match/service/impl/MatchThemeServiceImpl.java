package com.wonders.stpt.match.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wonders.stpt.match.dao.MatchThemeDao;
import com.wonders.stpt.match.domain.MatchTheme;
import com.wonders.stpt.match.service.IMatchThemeService;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageBounds;
import com.wonders.stpt.utils.paginator.mybatis.domain.PageList;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2014/8/19.
 */
@Service
public class MatchThemeServiceImpl implements IMatchThemeService {
    @Autowired
    private MatchThemeDao matchThemeDao;
    @Override 
    public PageList<MatchTheme> getThemes(MatchTheme theme, Integer pageIndex, Integer pageSize) throws Exception{
        if(pageIndex==null || pageSize==null)
        	return matchThemeDao.select(theme,new PageBounds());
    	
    	return matchThemeDao.select(theme,new PageBounds(pageIndex,pageSize));
    }
    
	@Override
	public void save(MatchTheme matchTheme, String[] excludes) throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(matchTheme.getMatchThemeId())){//更新
			MatchTheme mt=getMatchTheme(matchTheme.getMatchThemeId());
			BeanUtils.copyProperties(matchTheme, mt, excludes);
			matchThemeDao.update(mt, false);//false为非动态更新
		}else{//添加
			matchThemeDao.insert(matchTheme);
		}
	}

	@Override
	public MatchTheme getMatchTheme(String matchThemeId) throws Exception {
		
		MatchTheme mt=new MatchTheme();
		mt.setRemoved("0");
		mt.setMatchThemeId(matchThemeId);
		PageList<MatchTheme> mts=getThemes(mt,null,null);
		if(mts!=null && mts.size()==1){
			return mts.get(0);
		}
		return null;
	}

	@Override
	public int deletes(List<String> matchThemeIds) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		for(String matchThemeId:matchThemeIds ){
			MatchTheme theme=new MatchTheme();
			theme.setRemoved("1");
			theme.setMatchThemeId(matchThemeId);
			matchThemeDao.update(theme, true);//动态删除
			i++;
		}
		return i;
	}

	@Override
	public int deletes(String matchThemeId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> matchThemeIds=new ArrayList<String>();
		matchThemeIds.add(matchThemeId);
		return deletes(matchThemeIds);
	}
    
    
}
